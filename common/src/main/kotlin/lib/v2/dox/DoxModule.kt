package lib.v2.dox

import lib.core.ApiContext
import lib.core.ApiModule
import lib.v2.dox.endpoints.IpEndpoint

class DoxModule(context: ApiContext
) : ApiModule(context, "/dox") {
    val ipEndpoint = IpEndpoint(this)
}