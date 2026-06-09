package dev.elysium.eapi.lib.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import java.net.http.HttpResponse

typealias Endpoint<Req, Res> = EndpointWithQuery<Req, Unit, Res>
abstract class EndpointWithQuery<Req : Any, Query: Any, Res : Any>(
    private val module: ApiModule,
    private val reqSerializer: KSerializer<Req>?,
    private val querySerializer: KSerializer<Query>? = null,
    private val resSerializer: KSerializer<Res>
) {

    abstract val path: String

    fun queryToMap(query: Query?): Map<String, List<String>> {
        if (query == null || query is Unit || querySerializer == null) return emptyMap()

        // Используем kotlinx.serialization, чтобы превратить объект в Map
        val jsonTree = JsonProvider.json.encodeToJsonElement(querySerializer, query)

        // Превращаем JsonObject в плоскую Map<String, List<String>>
        return if (jsonTree is kotlinx.serialization.json.JsonObject) {
            jsonTree
                // ФИЛЬТРАЦИЯ: убираем из JSON-дерева все null значения
                .filterValues { value -> value !is kotlinx.serialization.json.JsonNull }
                .mapValues { (_, value) ->
                    // Используем contentOrNull, чтобы красиво достать строку без кавычек
                    val str = (value as? kotlinx.serialization.json.JsonPrimitive)?.content
                        ?: value.toString()
                    listOf(str)
                }
        } else {
            emptyMap()
        }
    }

    fun calcPath(path: String, paths: Map<String, String>): String {
        var currentPath = path

        for (i in paths.entries) {
            currentPath = currentPath.replace(":"+i.key, i.value)
        }
        return currentPath
    }

    open val method: HttpMethod = HttpMethod.POST

    suspend operator fun invoke(body: Req? = null, query: Query? = null, paths: Map<String, String> = mapOf()): Res {
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
            path = module.basePath + calcPath(path, paths),
            method = method,
            body = jsonBody,
            query = queryToMap(query)
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
