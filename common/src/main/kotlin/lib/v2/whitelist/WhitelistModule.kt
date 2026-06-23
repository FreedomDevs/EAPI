package dev.elysium.eapi.lib.v2.whitelist

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.dox.endpoints.IpEndpoint
import dev.elysium.eapi.lib.v2.whitelist.endpoints.AddEndpoint
import dev.elysium.eapi.lib.v2.whitelist.endpoints.CheckEndpoint
import dev.elysium.eapi.lib.v2.whitelist.endpoints.DeleteEndpoint

class WhitelistModule(context: ApiContext
) : ApiModule(context, "/whitelist") {
    val addEndpoint = AddEndpoint(this)
    val deleteEndpoint = DeleteEndpoint(this)
    val checkEndpoint = CheckEndpoint(this)
}
