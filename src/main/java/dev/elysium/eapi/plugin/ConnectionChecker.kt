package dev.elysium.eapi.plugin

import net.kyori.adventure.text.Component
import org.bukkit.scheduler.BukkitRunnable


class ConnectionChecker(val plugin: EAPIBukkit) : BukkitRunnable() {

    override fun run() {

        if(!plugin.apistatus) {
            plugin.RefreshApiStatus()

            if(plugin.apistatus) return

            plugin.logger.warning("Потеряно соединение с EAPI! Отключаю игроков.")

            for(player in plugin.server.onlinePlayers) {
                player.kick(Component.text("Потеряно соединение с EAPI!"))
            }
            return
        }

        plugin.RefreshApiStatus()
    }
}