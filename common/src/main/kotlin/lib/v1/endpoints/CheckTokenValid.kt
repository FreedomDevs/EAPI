package dev.elysium.eapi.lib.v1.endpoints

import dev.elysium.eapi.lib.v1.API
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object CheckTokenValid: Endpoint {
    private lateinit var api: API

     override fun inject(api: API) {
        this.api = api
    }

    @Serializable
    data class Response(
        val isValid: Boolean
    )

    suspend fun fetch(userToken: String): Response? {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("${api.baseUrl}/server-request/check/token"))
            .header("server-authorization", api.token)
            .POST(HttpRequest.BodyPublishers.ofString("{\"token\":\"${userToken}\"}"))
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
