package dev.elysium.eapi.lib.endpoints

import dev.elysium.eapi.lib.API

object GetHealth: Endpoint {
    private lateinit var api: API

    override fun inject(api: API) {
        this.api = api
    }

    fun fetch(playerName: String): String {
        TODO("Тут код для запроса")
        return playerName
    }
}