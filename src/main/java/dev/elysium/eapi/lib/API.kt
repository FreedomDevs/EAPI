package dev.elysium.eapi.lib

import dev.elysium.eapi.lib.endpoints.CheckTokenValid
import dev.elysium.eapi.lib.endpoints.GetHealth
import dev.elysium.eapi.lib.endpoints.GetUser

class API(internal val baseUrl: String, internal val token: String) {

  val getHealth = GetHealth
  val getUser = GetUser
  val checkTokenValid = CheckTokenValid

  init {
    getHealth.inject(this)
    getUser.inject(this)
    checkTokenValid.inject(this)
  }
}
