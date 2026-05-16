package dev.elysium.eapi.lib.core

import java.net.http.HttpRequest
import java.net.URI
import java.net.URLEncoder

class ApiContext(
    val baseUrl: String,
    val token: String
) {

    fun request(
        path: String,
        method: HttpMethod,
        body: String? = null,
        query: Map<String, String> = emptyMap()
    ): HttpRequest {

        val fullPath = buildString {
            append(baseUrl.trimEnd('/'))
            append(path)

            if (query.isNotEmpty()) {
                append('?')
                append(
                    query.entries.joinToString("&") { (key, value)  ->
                        "${URLEncoder.encode(key, Charsets.UTF_8)}=${URLEncoder.encode(value, Charsets.UTF_8)}"
                    }
                )
            }
        }

        val builder = HttpRequest.newBuilder()
            .uri(URI.create(fullPath))
            .header("Content-Type", "application/json")
            .header("Authorization", "Basic $token")

        return when (method) {

            HttpMethod.GET ->
                builder.GET().build()

            HttpMethod.POST ->
                builder.POST(
                    HttpRequest.BodyPublishers.ofString(body ?: "")
                ).build()

            HttpMethod.PUT ->
                builder.PUT(
                    HttpRequest.BodyPublishers.ofString(body ?: "")
                ).build()

            HttpMethod.DELETE ->
                builder.DELETE().build()
        }
    }
}