package dev.elysium.eapi

import me.lucko.configurate.toml.TOMLConfigurationLoader
import java.nio.file.Files
import java.nio.file.Path

class ConfigManager(private val dataDirectory: Path) {

    private val configFile = dataDirectory.resolve("config.toml").toFile()

    // Сюда мы сохраним объект конфига после загрузки
    // К нему мы и будем обращаться из других частей плагина
    lateinit var config: PluginConfig
        private set

    fun load() {
        // Создаем папку плагина, если её нет
        if (!Files.exists(dataDirectory)) {
            Files.createDirectories(dataDirectory)
        }

        // Настраиваем загрузчик для формата TOML
        val loader = TOMLConfigurationLoader.builder()
            .file(configFile)
            .build()

        // Читаем корень конфига
        val rootNode = loader.load()

        // Мапим TOML в наш Data-класс.
        // Если файла не было, он создаст дефолтный объект благодаря значениям по умолчанию.
        config = rootNode.get(PluginConfig::class.java) ?: PluginConfig()

        // Важный момент (замена copyDefaults):
        // Мы сразу сохраняем конфиг обратно. Если файла не было или появились новые поля,
        // они запишутся на диск со всеми дефолтами и комментариями!
        rootNode.set(PluginConfig::class.java, config)
        loader.save(rootNode)
    }
}