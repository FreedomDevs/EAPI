package dev.elysium.eapi.lib.v2.whitelist.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.Endpoint
import dev.elysium.eapi.lib.core.HttpMethod
import dev.elysium.eapi.lib.core.responses.ApiResponseND
import kotlinx.serialization.Serializable

class DeleteWhitelist(
    module: ApiModule
) : Endpoint<DeleteWhitelist.Req, ApiResponseND>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = ApiResponseND.serializer(),
) {
    override val path = "/"
    override val method = HttpMethod.DELETE

    @Serializable
    data class Req(
        val servername: String,
        val userid: EUUID
    )
}
