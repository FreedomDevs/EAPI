package dev.elysium.eapi.lib.core.responses

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T,
    val message: String,
    val meta: Meta
)