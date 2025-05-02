package dev.elysium.eapi.plugin.listeners

import dev.elysium.eapi.plugin.EAPIBukkit
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinPlayerApiHealthCheck : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        EAPIBukkit.Companion.instance.RefreshApiStatus()

        if (!EAPIBukkit.Companion.instance.apistatus) {
            event.player.kick(Component.text("Потеряно соединение с API!"))
        }
    }
}

