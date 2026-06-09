package dev.elysium.eapi.lib.v1.endpoints

import dev.elysium.eapi.lib.v1.API
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object GetUserTop: Endpoint {
    private lateinit var api: API

    override fun inject(api: API) {
        this.api = api
    }

    @Serializable
    data class Response(
        val name: String,
        val kills: Int,
        val deaths: Int,
        val playTime: Int
    )

    enum class TopType {
        playTime,
        deaths,
        kills
    }

    suspend fun fetch(type: TopType, limit: Int): List<Response>? {
        val client = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create("${api.baseUrl}/user-stats/top?type=$type&limit=$limit"))
            .header("server-authorization", api.token)
            .GET()
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() in 200..299) {
            Json.decodeFromString<List<Response>>(response.body())
        } else {
            println("Error: ${response.statusCode()} - ${response.body()}")
            null
        }
    }
}
