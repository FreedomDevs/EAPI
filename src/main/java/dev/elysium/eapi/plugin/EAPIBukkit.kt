package dev.elysium.eapi.plugin

import dev.elysium.eapi.lib.API
import dev.elysium.eapi.lib.endpoints.AddPlaytime
import dev.elysium.eapi.plugin.listeners.JoinPlayerApiHealthCheck
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class EAPIBukkit : JavaPlugin() {

  lateinit var api: API
  companion object {
    lateinit var instance: EAPIBukkit
      private set
  }

  var apistatus: Boolean = true

  @OptIn(DelicateCoroutinesApi::class)
  fun RefreshApiStatus() {
    GlobalScope.launch {
      var status = false
      status = (api.getHealth.fetch() != null)
      apistatus = status
    }
  }

  override fun onLoad() {
    instance = this
  }

  @OptIn(DelicateCoroutinesApi::class)
  override fun onEnable() {
    //    Config
    val config = config
    config.addDefault("baseUrl", "http://localhost:3000")
    config.addDefault("token", "secret-key")
    config.options().copyDefaults(true)
    saveConfig()

    api = API(config.getString("baseUrl").toString(), config.getString("token").toString())

    logger.info("EAPI включён!")

    server.pluginManager.registerEvents(JoinPlayerApiHealthCheck(), this)

    Bukkit.getScheduler()
            .runTaskTimer(this, ConnectionChecker(this), 0L, 15 * 20L /*15 секунд = 15*20 тиков*/)
  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }
}
