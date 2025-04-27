package dev.elysium.eapi.plugin

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.elysium.eapi.lib.listeners.JoinPlayerCheckToken
import org.bukkit.configuration.file.YamlConfiguration
import org.slf4j.Logger
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Objects
import javax.inject.Inject


@Plugin(id = "EApi", name = "EApi", version = "0.1")
class EAPIVelocity
@Inject
constructor(private val server: ProxyServer, private val logger: Logger, @DataDirectory private val dataDirectory: Path) {

    var key = ""
    var baseUrl = ""

    fun loadConfig() {
        val file = File(dataDirectory.toFile(), "config.yml")
        if(!file.exists()) {
            val filePath: String = Objects.requireNonNull(javaClass.getClassLoader().getResource("any.json")).getPath()
            val lines = Files.lines(Paths.get(filePath))

            for(line in lines) {
                file.writeText(line, StandardCharsets.UTF_8)
            }
        }

        val config = YamlConfiguration.loadConfiguration(file)
        config.save(file)

        key = config.getString("key").toString()
        baseUrl = config.getString("baseUrl").toString()

    }

    @Subscribe
    fun onProxyInitialize(event: ProxyInitializeEvent) {
        logger.info("Hello world!")
        loadConfig()

        server.eventManager.register(this, JoinPlayerCheckToken())
    }
}
