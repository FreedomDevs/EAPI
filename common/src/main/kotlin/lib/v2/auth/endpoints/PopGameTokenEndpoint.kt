package dev.elysium.eapi.lib.v2.auth.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint
import kotlinx.serialization.Serializable

class PopGameTokenEndpoint(
    module: ApiModule
) : Endpoint<PopGameTokenEndpoint.Req, PopGameTokenEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/pop_game_token"

    @Serializable
    data class Req(
        val game_token: String
    )

    @Serializable
    data class Res(
        val uuid: String,
        val username: String
    )
}
