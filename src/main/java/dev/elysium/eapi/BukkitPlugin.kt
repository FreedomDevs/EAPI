package dev.elysium.eapi

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

  override fun onEnable() {
    logger.info("EAPI включён!")
    logger.info("Hello World!")
  }

  override fun onDisable() {
    logger.info("EAPI выключен.")
  }
}
