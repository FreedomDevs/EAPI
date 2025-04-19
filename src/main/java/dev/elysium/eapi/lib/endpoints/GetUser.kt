package dev.elysium.eapi.lib.endpoints

import dev.elysium.eapi.lib.API
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GetUser: Endpoint {
    private lateinit var api: API
    private val client = HttpClient() {
        expectSuccess = true
    }

    @Serializable
    data class UserResponse(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val avatar: String,
        val skinUrl: String,
        val skinType: Boolean,
        val createdAt: String,
        val updatedAt: String,
        val roles: List<String>
    )

    override fun inject(api: API) {
        this.api = api
    }

    suspend fun fetch(playerName: String): UserResponse {
        return withContext(Dispatchers.IO) {
            client.post("${api.baseUrl}/server-request/get/user/$playerName") {
                contentType(ContentType.Application.Json)
                header("server-authorization", api.token)
                header("Accept", "application/json")
            }.body()
        }
    }
}