package dev.elysium.eapi.lib.v2.users.endpoints

import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.core.EndpointWithQuery
import dev.elysium.eapi.lib.core.HttpMethod
import kotlinx.serialization.Serializable

class UserByIdOrName (
    module: ApiModule
) : EndpointWithQuery<Unit, UserByIdOrName.Query, UserByIdOrName.Res>(
    module = module,
    reqSerializer = null,
    resSerializer = Res.serializer(),
) {
    override val path = "/:idOrName"
    override val method = HttpMethod.GET

    @Serializable
    data class Query(
        val psw: String,
    )

    @Serializable
    data class Res(
        val success: Boolean,
        val code: String,
        val data: UserData
    )

    @Serializable
    data class UserData(
        val id: String,
        val name: String,
        val password: String? = null,
        val roles: List<String>,
        val createdAt: String,
        val updatedAt: String,
    )
}