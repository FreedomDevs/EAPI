package dev.elysium.eapi.lib.endpoints

import dev.elysium.eapi.lib.API
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object BanUser: Endpoint {
    private lateinit var api: API

    override fun inject(api: API) {
        this.api = api
    }

    @Serializable
    data class Response(
        val userId: String
    )

    @Serializable
    enum class IssuedBy {
        EAC,
        MODERATOR,
        CONSOLE
    }

    @Serializable
    data class RequestBody(
        val name: String,
        val reason: String,
        val issuedBy: IssuedBy,
        val expiresAt: String
    )

    suspend fun fetch(requestBody: RequestBody): Response? {
        val client = HttpClient.newHttpClient()
        val jsonBody = Json.encodeToString(requestBody)

        val request = HttpRequest.newBuilder()
            .uri(URI.create("${api.baseUrl}/server-request/ban/ban"))
            .header("server-authorization", api.token)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
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