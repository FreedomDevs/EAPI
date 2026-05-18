class PluginConfig(private val plugin: EAPIPaper) {

    fun init() {
        val config = plugin.config

        config.addDefault("baseUrl", "http://localhost:3000")
        config.addDefault("token", "secret-key")

        config.options().copyDefaults(true)
        plugin.saveConfig()
    }

    fun baseUrl(): String =
        plugin.config.getString("baseUrl", "http://localhost:3000").toString()

    fun token(): String =
        plugin.config.getString("token", "secret-key").toString()
}