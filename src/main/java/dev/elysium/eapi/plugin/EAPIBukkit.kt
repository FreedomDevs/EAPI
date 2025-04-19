package dev.elysium.eapi.plugin

import dev.elysium.eapi.lib.API
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bukkit.plugin.java.JavaPlugin

class EAPIBukkit : JavaPlugin()  {

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
    config.addDefault("baseUrl", "http://localhost:3000")
    config.addDefault("token", "server-token")
    config.options().copyDefaults(true)
    saveConfig()

    api = API(config.getString("baseUrl").toString(), config.getString("token").toString())

    logger.info("EAPI включён!")

    GlobalScope.launch {
      val res = api.getUser.fetch("foksik")
      logger.info(res?.email)
    }

  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }
}
