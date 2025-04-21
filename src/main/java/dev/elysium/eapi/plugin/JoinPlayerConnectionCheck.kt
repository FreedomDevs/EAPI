package dev.elysium.eapi.plugin

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinPlayerConnectionCheck: Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        EAPIBukkit.instance.RefreshApiStatus()

        if (!EAPIBukkit.instance.apistatus) {
            event.player.kick(Component.text("Потеряно соединение с EAPI!"))
        }
    }

}