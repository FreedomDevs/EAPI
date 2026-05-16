package dev.elysium.eapi.lib.v2.auth.endpoints

import kotlinx.serialization.Serializable
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint


class LoginEndpoint(
    module: ApiModule
) : Endpoint<LoginEndpoint.Req, LoginEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/login"

    @Serializable
    data class Req(
        val login: String,
        val password: String
    )

    @Serializable
    data class Res(
        val access_token: String,
        val refresh_token: String
    )
}