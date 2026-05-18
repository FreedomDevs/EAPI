package dev.elysium.eapi.lib.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import java.net.http.HttpResponse

abstract class Endpoint<Req : Any, Res : Any>(
    private val module: ApiModule,
    private val reqSerializer: KSerializer<Req>?,
    private val resSerializer: KSerializer<Res>
) {

    abstract val path: String

    open fun query(body: Req?): Map<String, String> = emptyMap()

    open val method: HttpMethod = HttpMethod.POST

    suspend operator fun invoke(body: Req? = null): Res {

        val jsonBody =
            if (body != null && reqSerializer != null) {
                JsonProvider.json.encodeToString(
                    reqSerializer,
                    body
                )
            } else {
                null
            }

        val request = module.context.request(
            path = module.basePath + path,
            method = method,
            body = jsonBody,
            query = query(body)
        )

        val response = withContext(Dispatchers.IO) {
            HttpClientProvider.client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            )
        }

        if (response.statusCode() !in 200..299) {
            throw ApiException(
                response.statusCode(),
                response.body()
            )
        }

        return JsonProvider.json.decodeFromString(
            resSerializer,
            response.body()
        )
    }
}
