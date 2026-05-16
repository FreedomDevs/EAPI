package dev.elysium.eapi.lib.core

import java.net.http.HttpClient

object HttpClientProvider {
    val client: HttpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build()
}