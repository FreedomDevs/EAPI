package dev.elysium.eapi.lib.v2.whitelist

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.whitelist.endpoints.AddWhitelist

class WhitelistModule(
    context: ApiContext
) : ApiModule(context, "/whitelist") {
    val addWhitelist = AddWhitelist(this)

}