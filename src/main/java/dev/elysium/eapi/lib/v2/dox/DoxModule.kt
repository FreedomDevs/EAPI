package dev.elysium.eapi.lib.v2.dox

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.dox.endpoints.IpEndpoint

class DoxModule(context: ApiContext
) : ApiModule(context, "/dox") {
    val ipEndpoint = IpEndpoint(this)
}