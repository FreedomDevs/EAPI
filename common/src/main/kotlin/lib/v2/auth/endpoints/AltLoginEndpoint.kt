package dev.elysium.eapi.lib.v2.auth.endpoints

import kotlinx.serialization.Serializable
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint


class AltLoginEndpoint(
    module: ApiModule
) : Endpoint<AltLoginEndpoint.Req, AltLoginEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/alt/login"

    @Serializable
    data class Req(
        val login: String,
        val password: String
    )

    @Serializable
    data class Res(
        val refresh_token: String
    )
}
