package dev.elysium.eapi.lib.core.responses

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val code: String,
    val traceId: String,
    val timestamp: String
)