package dev.elysium.eapi.lib

class API(private val baseUrl: String, private val token: String) {
  fun getBaseUrl(): String {
    return baseUrl
  }
}
