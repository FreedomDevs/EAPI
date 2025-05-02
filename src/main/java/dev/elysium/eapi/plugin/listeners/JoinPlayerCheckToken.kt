package dev.elysium.eapi.plugin.listeners

import com.velocitypowered.api.event.Subscribe
import dev.elysium.eapi.lib.endpoints.CheckTokenValid
import net.kyori.adventure.text.Component
import org.bukkit.event.player.PlayerJoinEvent

class JoinPlayerCheckToken {
    @Subscribe
    public suspend fun onPlayerJoin(event: PlayerJoinEvent) {
        val uuid = event.player.identity().uuid()

        if (CheckTokenValid.fetch(uuid.toString()) == null)
                event.player.kick(Component.text("Invalid Token!"))
    }
}
