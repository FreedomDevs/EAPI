package dev.elysium.eapi

import com.google.inject.Inject
import com.velocitypowered.api.event.EventManager
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.elysium.eapi.lib.API
import org.slf4j.Logger
import java.nio.file.Path

@Plugin(
    id = "eapi",
    name = "EAPI",
    version = "1.0.0",
    description = "EAPI Velocity plugin",
    authors = ["mikinol"]
)
class EAPIVelocity @Inject constructor(
    val server: ProxyServer,
    val logger: Logger,
    private val eventManager: EventManager,
    @DataDirectory val dataDirectory: Path
) {
    private val configManager = ConfigManager(dataDirectory)
    lateinit var api: API
        private set

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        instance = this
        try {
            configManager.load()
            logger.info("Конфиг успешно инициализирован!")
        } catch (e: Exception) {
            logger.error("Не удалось загрузить конфиг! Плагин выключается.", e)
            return
        }

        api = API(configManager.config.baseUrl, configManager.config.token)
        eventManager.register(this, UUIDChanger())
        logger.info("EAPI Velocity успешно инициализирован!")
    }

    companion object {
        @Volatile
        lateinit var instance: EAPIVelocity
            private set
    }
}