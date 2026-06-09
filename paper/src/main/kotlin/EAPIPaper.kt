package dev.elysium.eapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import dev.elysium.eapi.lib.API
import dev.elysium.eapi.listeners.JoinPlayerApiHealthCheck
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import dev.elysium.eapi.services.ApiHealthService
import dev.elysium.eapi.services.ConnectionCheckerService

class EAPIPaper: JavaPlugin(), CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext =
        Dispatchers.IO + job

    lateinit var api: API
        private set

    lateinit var apiHealthService: ApiHealthService
        private set

    private lateinit var pluginConfig: PluginConfig

    companion object {
        @Volatile
        lateinit var instance: EAPIPaper
            private set
    }

    override fun onLoad() {
        instance = this
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onEnable() {
        setupConfig()
        setupApi()
        setupServices()
        logger.info("EAPI включён!")
    }

    override fun onDisable() {
        job.cancel()
        logger.info("EAPI выключен.")
    }

    private fun setupConfig() {
        pluginConfig = PluginConfig(this)
        pluginConfig.init()
    }

    private fun setupApi() {
        api = API(
            pluginConfig.baseUrl(),
            pluginConfig.token()
        )
    }

    private fun setupServices() {
        apiHealthService = ApiHealthService(this, api)

        server.pluginManager.registerEvents(
            JoinPlayerApiHealthCheck(apiHealthService),
            this
        )

        Bukkit.getScheduler().runTaskTimer(
            this,
            ConnectionCheckerService(apiHealthService),
            0L,
            15 * 20L
        )
    }
}
