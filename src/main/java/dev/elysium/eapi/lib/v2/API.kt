package dev.elysium.eapi.lib.v2

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.v2.auth.AuthModule

class API(baseUrl: String, token: String) {

    private val context = ApiContext(baseUrl, token)

    val auth = AuthModule(context)
}