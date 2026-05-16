package dev.elysium.eapi.plugin.services

import org.bukkit.scheduler.BukkitRunnable

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
        val plugin = dev.elysium.eapi.plugin.EAPIBukkit.instance

        plugin.logger.warning("Потеряно соединение с API! Отключаю игроков.")

        plugin.server.onlinePlayers.forEach {
            it.kick(net.kyori.adventure.text.Component.text("Потеряно соединение с API!"))
        }
    }
}