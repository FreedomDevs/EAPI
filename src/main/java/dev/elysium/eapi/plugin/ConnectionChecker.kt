package dev.elysium.eapi.plugin

import kotlinx.coroutines.Runnable
import net.kyori.adventure.text.Component


class ConnectionChecker(val plugin: EAPIBukkit) : Runnable {

    override fun run() {

        if(!plugin.apistatus) {
            plugin.RefreshApiStatus()

            if(plugin.apistatus) return

            plugin.logger.warning("Потеряно соединение с API! Отключаю игроков.")

            for(player in plugin.server.onlinePlayers) {
                player.kick(Component.text("Потеряно соединение с API!"))
            }
            return
        }

        plugin.RefreshApiStatus()
    }
} // вот это запускается раз в 15 секунд