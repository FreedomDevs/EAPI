package dev.elysium.eapi.lib

import dev.elysium.eapi.lib.endpoints.AddDeaths
import dev.elysium.eapi.lib.endpoints.AddKills
import dev.elysium.eapi.lib.endpoints.AddPlaytime
import dev.elysium.eapi.lib.endpoints.BanUser
import dev.elysium.eapi.lib.endpoints.CheckTokenValid
import dev.elysium.eapi.lib.endpoints.GetHealth
import dev.elysium.eapi.lib.endpoints.GetUser
import dev.elysium.eapi.lib.endpoints.GetUserTop
import dev.elysium.eapi.lib.endpoints.UnbanUser

class API(internal val baseUrl: String, internal val token: String) {

  val getHealth by lazy {
    GetHealth.apply { inject(this@API) }
  }

  val getUser by lazy {
    GetUser.apply { inject(this@API) }
  }

  val checkTokenValid by lazy {
    CheckTokenValid.apply { inject(this@API) }
  }

  val addKills by lazy {
    AddKills.apply { inject(this@API) }
  }

  val addDeaths by lazy {
    AddDeaths.apply { inject(this@API) }
  }

  val addPlaytime by lazy {
    AddPlaytime.apply { inject(this@API) }
  }

  val banUser by lazy {
    BanUser.apply { inject(this@API) }
  }

  val unbanUser by lazy {
    UnbanUser.apply { inject(this@API) }
  }

  val getUserTop by lazy {
    GetUserTop.apply { inject(this@API) }
  }
}
