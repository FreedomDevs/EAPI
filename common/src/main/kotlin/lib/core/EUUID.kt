package dev.elysium.eapi.lib.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

@Serializable(with = EUUID.Serializer::class)
data class EUUID(val value: UUID) {
    override fun toString(): String = value.toString()

    companion object {
        fun fromString(str: String): EUUID {
            return try {
                EUUID(UUID.fromString(str))
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("Invalid UUID format")
            }
        }
    }

    object Serializer : KSerializer<EUUID> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("EUUID", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: EUUID) {
            encoder.encodeString(value.value.toString())
        }

        override fun deserialize(decoder: Decoder): EUUID {
            val raw = decoder.decodeString()
            return fromString(raw)
        }
    }
}