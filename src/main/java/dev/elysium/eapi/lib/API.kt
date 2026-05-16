package dev.elysium.eapi.lib

class API(internal val baseUrl: String, internal val token: String) {

    fun v1() =
        dev.elysium.eapi.lib.v1.API(baseUrl, token)

    fun v2() =
        dev.elysium.eapi.lib.v2.API(baseUrl, token)
}