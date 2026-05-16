package dev.elysium.eapi.plugin.listeners

import dev.elysium.eapi.plugin.services.ApiHealthService
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinPlayerApiHealthCheck(
    private val apiHealth: ApiHealthService
) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        apiHealth.refreshAsync()

        if (!apiHealth.status) {
            event.player.kick(Component.text("Потеряно соединение с API!"))
        }
    }
}