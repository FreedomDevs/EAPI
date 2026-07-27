package dev.elysium.eapi.lib.v2.whitelist.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.Serializable
import dev.elysium.eapi.lib.core.responses.ApiResponse

class CheckWhitelist(
    module: ApiModule
) : EndpointWithQuery<Unit, CheckWhitelist.Query, ApiResponse<CheckWhitelist.Res>>(
    module = module,
    reqSerializer = null,
    resSerializer = ApiResponse.serializer(Res.serializer()),
) {
    override val path = "/"
    override val method = HttpMethod.POST

    @Serializable
    data class Query(
        val userid: EUUID,
        val servers: List<String>
    )

    @Serializable
    data class Res(
        val in_whitelist: Boolean,
    )
}