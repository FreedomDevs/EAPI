package dev.elysium.eapi.plugin

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import javax.inject.Inject
import org.slf4j.Logger

@Plugin(id = "EApi", name = "EApi", version = "0.1")
class ExamplePlugin
@Inject
constructor(private val server: ProxyServer, private val logger: Logger) {
    @Subscribe
    fun onProxyInitialize(event: ProxyInitializeEvent) {
        logger.info("Hello world!")
    }
}
