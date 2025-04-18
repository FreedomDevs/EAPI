package dev.elysium.eapi.lib

import dev.elysium.eapi.lib.endpoints.GetHealth

class API(private val baseUrl: String, private val token: String) {

  val getHealth = GetHealth

  init {
    getHealth.inject(this)
  }
}
