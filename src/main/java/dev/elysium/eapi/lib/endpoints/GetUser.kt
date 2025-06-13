package dev.elysium.eapi.lib.endpoints

import dev.elysium.eapi.lib.API
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object GetUser : Endpoint {
    private lateinit var api: API

    override fun inject(api: API) {
        this.api = api
    }

    @Serializable
    data class Response(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val avatar: String? = null,
        val skinUrl: String? = null,
        val skinType: Boolean,
        val coins: Int,
        val pass: Boolean,
        val roles: List<String>,

        val createdAt: String,
        val updatedAt: String
    )

    suspend fun fetch(playerName: String): Response? {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("${api.baseUrl}/server-request/get/user/$playerName"))
            .header("server-authorization", api.token)
            .GET()
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() in 200..299) {
            Json.decodeFromString<Response>(response.body())
        } else {
            println("Error: ${response.statusCode()} - ${response.body()}")
            null
        }
    }
}
