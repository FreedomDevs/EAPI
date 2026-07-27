package dev.elysium.eapi.lib.v2.auth

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.v2.auth.endpoints.*
import dev.elysium.eapi.lib.core.ApiModule

@Suppress("Unused")
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
