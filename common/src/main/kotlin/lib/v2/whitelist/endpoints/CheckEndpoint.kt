package dev.elysium.eapi.lib.v2.whitelist.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class CheckEndpoint(
    module: ApiModule
) : EndpointWithQuery<Unit, CheckEndpoint.Query, CheckEndpoint.Res>(
    module = module,
    reqSerializer = null,
    resSerializer = Res.serializer(),
) {

    override val path = "/check"
    override val method = HttpMethod.GET

    @Serializable
    data class Query(
        val servername: String? = null,
        val userid: EUUID
    )

    @Serializable
    data class Res(

        val success: Boolean,

        val code: String,

        val data: CheckData
    )

    @Serializable
    data class CheckData(

        val in_whitelist: Boolean

    )
}