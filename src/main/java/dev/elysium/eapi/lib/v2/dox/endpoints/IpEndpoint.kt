package dev.elysium.eapi.lib.v2.dox.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.Endpoint
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

class IpEndpoint(
    module: ApiModule
) : Endpoint<IpEndpoint.Req, IpEndpoint.Res>(
    module = module,
    reqSerializer = null,
    resSerializer = Res.serializer(),
) {

    override val path = "/ip"
    override val method = HttpMethod.GET

    override fun query(body: Req?): Map<String, String> {
        if (body == null) {
            return emptyMap()
        }

        return buildMap {
            put("ip", body.ip)
            body.lang?.let {
                put("lang", it)
            }
        }
    }

    @Serializable
    data class Req(
        val ip: String,
        val lang: String? = "ru"
    )

    @Serializable
    data class Res(

        val success: Boolean,

        val code: String,

        val data: List<IpInfo>
    )

    @Serializable
    data class IpInfo(

        val continent_code: String? = null,

        val continent: String? = null,

        val is_in_european_union: Boolean? = null,

        val country_code: String? = null,

        val country: String? = null,

        val subdivisions: List<String>? = null,

        val city: String? = null,

        val latitude: Double? = null,

        val longitude: Double? = null,

        @SerialName("ASN")
        val asn: Long? = null,

        @SerialName("ASO")
        val aso: String? = null,

        val result: String? = null
    )
}