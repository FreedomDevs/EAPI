package dev.elysium.eapi.lib.v2.whitelist

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.whitelist.endpoints.*

@Suppress("Unused")
class WhitelistModule(
    context: ApiContext
) : ApiModule(context, "/whitelist") {
    val addWhitelist = AddWhitelist(this)
    val checkWhitelist = CheckWhitelist(this)
    val deleteWhitelist = DeleteWhitelist(this)
}