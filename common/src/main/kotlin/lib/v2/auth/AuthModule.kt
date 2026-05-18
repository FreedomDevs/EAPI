package lib.v2.auth

import lib.core.ApiContext
import lib.v2.auth.endpoints.*
import lib.core.ApiModule

class AuthModule(
    context: ApiContext
) : ApiModule(context, "/auth") {

    val login = LoginEndpoint(this)
    val altLogin = AltLoginEndpoint(this)

    val register = RegisterEndpoint(this)
    val altRegister = AltRegisterEndpoint(this)

    val refresh = RefreshEndpoint(this)
    val popGameToken = PopGameTokenEndpoint(this)
    val checkRefreshToken = CheckRefreshTokenEndpoint(this)
}
