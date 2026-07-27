package dev.elysium.eapi.lib.v2

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.v2.auth.AuthModule
import dev.elysium.eapi.lib.v2.dox.DoxModule
import dev.elysium.eapi.lib.v2.punishments.PunishmentsModule
import dev.elysium.eapi.lib.v2.users.UsersModule
import dev.elysium.eapi.lib.v2.whitelist.WhitelistModule

@Suppress("Unused")
class API(baseUrl: String, token: String) {

    private val context = ApiContext(baseUrl, token)

    val auth = AuthModule(this.context)

    val dox = DoxModule(this.context)

    val users = UsersModule(this.context)

    val punishments = PunishmentsModule(this.context)

    val whitelist = WhitelistModule(this.context)
}
