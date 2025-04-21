package dev.elysium.eapi.plugin

import dev.elysium.eapi.lib.API
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class EAPIBukkit : JavaPlugin()  {

  lateinit var api: API
  companion object {
    lateinit var instance: EAPIBukkit
      private set
  }

  var apistatus: Boolean = true

  @OptIn(DelicateCoroutinesApi::class)
  fun RefreshApiStatus() {
    var status = false

    GlobalScope.launch {
      status =  (api.getHealth.fetch() != null)
    }

    apistatus = status
  }


  override fun onLoad() {
    instance = this
  }

  @OptIn(DelicateCoroutinesApi::class)
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

    server.pluginManager.registerEvents(JoinPlayerConnectionCheck(), this)

    Bukkit.getScheduler().runTaskTimer(this, ConnectionChecker(this), 0L, 15*20L /*15 секунд = 15*20 тиков*/)
    
  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }
}
