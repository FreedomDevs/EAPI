package lib.v2.auth.endpoints

import kotlinx.serialization.Serializable
import lib.core.ApiModule
import lib.core.Endpoint

class AltRegisterEndpoint(
    module: ApiModule
) : Endpoint<AltRegisterEndpoint.Req, AltRegisterEndpoint.Res>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = Res.serializer(),
) {

    override val path = "/alt/register"

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