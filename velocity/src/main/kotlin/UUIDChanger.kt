package dev.elysium.eapi

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.GameProfileRequestEvent
import com.velocitypowered.api.util.GameProfile
import dev.elysium.eapi.lib.core.ApiException
import dev.elysium.eapi.lib.v2.users.endpoints.UserByIdOrName
import kotlinx.coroutines.runBlocking
import java.security.SecureRandom
import java.util.*


class UUIDChanger {
    private val random: SecureRandom = SecureRandom()

    fun generateUnregisteredV8Uuid(): UUID {
        val bytes = ByteArray(16)
        random.nextBytes(bytes)

        // 1. Вшиваем префикс "454c5953-4955" (первые 6 байт)
        // 45 4C 59 53
        bytes[0] = 0x45.toByte()
        bytes[1] = 0x4C.toByte()
        bytes[2] = 0x59.toByte()
        bytes[3] = 0x53.toByte()
        // 49 55
        bytes[4] = 0x49.toByte()
        bytes[5] = 0x55.toByte()

        // 2. Устанавливаем версию 8 (биты 4-7 в 6-м байте)
        // Так как 6-й байт идет сразу после префикса, ставим туда версию
        bytes[6] = (bytes[6].toInt() and 0x0F).toByte()
        bytes[6] = (bytes[6].toInt() or 0x80).toByte() // 0x80 задает '8' в шестнадцатеричном представлении версии

        // 3. Устанавливаем вариант RFC 4122 (биты 6-7 в 8-м байте равны 10)
        bytes[8] = (bytes[8].toInt() and 0x3F).toByte()
        bytes[8] = (bytes[8].toInt() or 0x80).toByte()

        // 4. Собираем UUID
        var msb: Long = 0
        var lsb: Long = 0
        for (i in 0..7) msb = (msb shl 8) or (bytes[i].toInt() and 0xff).toLong()
        for (i in 8..15) lsb = (lsb shl 8) or (bytes[i].toInt() and 0xff).toLong()

        return UUID(msb, lsb)
    }

    @Subscribe
    fun onGameProfileRequest(event: GameProfileRequestEvent) {
        val originalProfile = event.gameProfile
        val username = originalProfile.name

        val response: UserByIdOrName.Res
        try {
            response = runBlocking {
                EAPIVelocity.instance.api.v2.users.userByIdOrNameEndpoint(paths = mapOf(Pair(":idOrName", username)))
            }
        } catch (ex: ApiException) {
            if (ex.status == 404) {
                val uuid = generateUnregisteredV8Uuid()
                val newProfile = GameProfile(uuid, username, originalProfile.properties)
                event.gameProfile = newProfile
                return
            }

            throw RuntimeException("Неизвестная API ошибка "+ex.status + " "+ex.message)
        }

        val customUuid = UUID.fromString(response.data.id)
        val newProfile = GameProfile(customUuid, username, originalProfile.properties)

        event.gameProfile = newProfile
    }
}