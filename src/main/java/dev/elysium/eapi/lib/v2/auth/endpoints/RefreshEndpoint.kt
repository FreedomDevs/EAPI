package dev.elysium.eapi.lib.v2.auth.endpoints

import kotlinx.serialization.Serializable
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint
import dev.elysium.eapi.lib.v2.auth.RefreshMethod

class RefreshEndpoint(
    module: ApiModule
) : Endpoint<RefreshEndpoint.Req, RefreshEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/refresh"

    @Serializable
    data class Req(
        val refresh_token: String,
        val method: RefreshMethod
    )

    @Serializable
    data class Res(
        val token: String
    )
}