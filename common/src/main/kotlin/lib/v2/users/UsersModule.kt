package dev.elysium.eapi.lib.v2.users

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.users.endpoints.UserByIdOrName

class UsersModule(context: ApiContext
) : ApiModule(context, "/users") {
    val userByIdOrNameEndpoint = UserByIdOrName(this)
}
