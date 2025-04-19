package dev.elysium.eapi.lib

import dev.elysium.eapi.lib.endpoints.GetHealth
import dev.elysium.eapi.lib.endpoints.GetUser

class API(internal val baseUrl: String, internal val token: String) {

  val getHealth = GetHealth
  val getUser = GetUser

  init {
    getHealth.inject(this)
    getUser.inject(this)
  }
}
