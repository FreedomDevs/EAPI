package dev.elysium.eapi.services

import org.bukkit.scheduler.BukkitRunnable
import dev.elysium.eapi.EAPIPaper

class ConnectionCheckerService(
    private val apiHealth: ApiHealthService
) : BukkitRunnable() {

    override fun run() {
        apiHealth.refreshAsync()

        if (!apiHealth.status) {
            apiHealthDown()
        }
    }

    private fun apiHealthDown() {
        val plugin = EAPIPaper.instance

        plugin.logger.warning("Потеряно соединение с API! Отключаю игроков.")

        plugin.server.onlinePlayers.forEach {
            it.kick(net.kyori.adventure.text.Component.text("Потеряно соединение с API!"))
        }
    }
}
