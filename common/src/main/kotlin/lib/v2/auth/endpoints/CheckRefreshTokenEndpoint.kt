package dev.elysium.eapi.lib.v2.auth.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint
import kotlinx.serialization.Serializable

class CheckRefreshTokenEndpoint(
    module: ApiModule
) : Endpoint<CheckRefreshTokenEndpoint.Req, Unit>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = null,
) {

    override val path = "/check_refresh_token"

    @Serializable
    data class Req(
        val refresh_token: String
    )
}
