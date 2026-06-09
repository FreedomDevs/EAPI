package dev.elysium.eapi

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
data class PluginConfig(
    @Comment("Базовый URL для запросов к API")
    val baseUrl: String = "http://localhost:3000",

    @Comment("Секретный токен авторизации")
    val token: String = "secret-key"
)