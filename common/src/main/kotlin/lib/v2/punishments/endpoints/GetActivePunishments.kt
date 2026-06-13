package dev.elysium.eapi.lib.v2.punishments.endpoints

import dev.elysium.eapi.lib.core.responses.ApiResponse
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import dev.elysium.eapi.lib.v2.punishments.Issued
import dev.elysium.eapi.lib.v2.punishments.Types
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

class GetActivePunishments(
    module: ApiModule
) : EndpointWithQuery<Unit, GetActivePunishments.Query, ApiResponse<List<GetActivePunishments.Res>>>(
    module = module,
    reqSerializer = null,
    resSerializer = ApiResponse.serializer(ListSerializer(Res.serializer())),
) {
    override val path = "/check"
    override val method = HttpMethod.GET

    @Serializable
    data class Query(
        val userId: EUUID,
        val serverName: String
    )

    @Serializable
    data class Res(
        val type: Types,
        val reason: String,
        val issuedBy: EUUID,
        val expiresAt: String,
        val createdAt: String,
        val issued: Issued
    )
}