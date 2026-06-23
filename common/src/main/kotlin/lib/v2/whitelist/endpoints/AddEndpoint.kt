package dev.elysium.eapi.lib.v2.whitelist.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.Endpoint
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

class AddEndpoint(
    module: ApiModule
) : Endpoint<AddEndpoint.Req, AddEndpoint.Res>(
    module = module,
    reqSerializer = null,
    resSerializer = Res.serializer(),
) {

    override val path = ""
    override val method = HttpMethod.POST

    @Serializable
    data class Req(
        val servername: String? = null,
        val userid: EUUID

    )

    @Serializable
    data class Res(

        val success: Boolean,

        val code: String,

        val data: Unit
    )
}

