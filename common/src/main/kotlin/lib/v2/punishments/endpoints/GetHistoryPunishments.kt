package dev.elysium.eapi.lib.v2.punishments.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import dev.elysium.eapi.lib.core.responses.ApiResponse
import dev.elysium.eapi.lib.v2.punishments.Issued
import dev.elysium.eapi.lib.v2.punishments.Types
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

class GetHistoryPunishments(
    module: ApiModule
) : EndpointWithQuery<Unit, GetHistoryPunishments.Query, ApiResponse<List<GetHistoryPunishments.Res>>>(
    module = module,
    reqSerializer = null,
    resSerializer = ApiResponse.serializer(ListSerializer(Res.serializer())),
) {
    override val path = "/history"
    override val method = HttpMethod.GET

    @Serializable
    data class Query(
        val userId: EUUID
    )

    @Serializable
    data class Res(
        val id: EUUID,
        val type: Types,
        val reason: String,
        val issuedBy: EUUID,
        val serverName: String,
        val createdAt: String,
        val expiresAt: String?,
        val revokedAt: String?,
        val issued: Issued,
        val revokedBy: EUUID?,
        val revokedReason: String?
    )
}