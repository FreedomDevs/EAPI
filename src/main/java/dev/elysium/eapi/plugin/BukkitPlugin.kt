package dev.elysium.eapi.plugin

import dev.elysium.eapi.lib.API
import org.bukkit.plugin.java.JavaPlugin

class EAPIBukkit : JavaPlugin() {
  lateinit var api: API
  companion object {
    lateinit var instance: EAPIBukkit
      private set
  }

  override fun onLoad() {
    instance = this
  }

  override fun onEnable() {
    val config = config

    // Устанавливаем дефолтные значения
    config.addDefault("baseUrl", "https://example.com")
    config.addDefault("token", "SECRET_TOKEN")
    config.options().copyDefaults(true)

    saveConfig()

    api = API(config.getString("baseUrl").toString(), config.getString("token").toString())

    logger.info("BaseUrl: " + api.getBaseUrl())
    logger.info("EAPI включён!")
  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }
}
