package lib.v2.auth.endpoints

import lib.core.ApiModule
import lib.core.Endpoint
import kotlinx.serialization.Serializable

class CheckRefreshTokenEndpoint(
    module: ApiModule
) : Endpoint<CheckRefreshTokenEndpoint.Req, CheckRefreshTokenEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/check_refresh_token"

    @Serializable
    data class Req(
        val refresh_token: String
    )

    @Serializable
    data class Res(
        val uuid: String,
        val username: String
    )
}