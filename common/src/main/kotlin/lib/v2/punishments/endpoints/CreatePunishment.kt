package dev.elysium.eapi.lib.v2.punishments.endpoints

import dev.elysium.eapi.lib.core.responses.ApiResponse
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EUUID
import dev.elysium.eapi.lib.core.Endpoint
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.Serializable

class CreatePunishment (
    module: ApiModule
) : Endpoint<CreatePunishment.Req, ApiResponse<CreatePunishment.Res>>(
    module = module,
    reqSerializer = Req.serializer(),
    resSerializer = ApiResponse.serializer(Res.serializer()),
) {
    override val path = "/:punishmentId"
    override val method = HttpMethod.POST

    @Serializable
    data class Req(
        val revokedBy: EUUID,
        val reason: String,
    )

    @Serializable
    data class Res(
        val punishmentId: EUUID
    )
}