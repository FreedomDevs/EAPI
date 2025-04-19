package dev.elysium.eapi.plugin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dev.elysium.eapi.lib.API
import org.bukkit.plugin.java.JavaPlugin

class EAPIBukkit : JavaPlugin(), CoroutineScope  {
  override val coroutineContext = Dispatchers.IO

  lateinit var api: API
  companion object {
    lateinit var instance: EAPIBukkit
      private set
  }

  override fun onLoad() {
    instance = this
  }

  override fun onEnable() {
//    Config
    val config = config
    config.addDefault("baseUrl", "https://example.com")
    config.addDefault("token", "SECRET_TOKEN")
    config.options().copyDefaults(true)
    saveConfig()

    api = API(config.getString("baseUrl").toString(), config.getString("token").toString())

    logger.info("EAPI включён!")

    launch {
      tesy()
    }
  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }

  suspend fun tesy(){
    val s = api.getUser.fetch("foksik")
    logger.info(s.id)
  }
}
