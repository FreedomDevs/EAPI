package dev.elysium.eapi.lib.v2.punishments

import dev.elysium.eapi.lib.core.ApiContext
import dev.elysium.eapi.lib.core.ApiModule
import dev.elysium.eapi.lib.v2.punishments.endpoints.CreatePunishment
import dev.elysium.eapi.lib.v2.punishments.endpoints.GetActivePunishments
import dev.elysium.eapi.lib.v2.punishments.endpoints.GetHistoryPunishments
import dev.elysium.eapi.lib.v2.punishments.endpoints.RevokePunishment

@Suppress("Unused")
class PunishmentsModule(context: ApiContext
) : ApiModule(context, "/punishments") {
    val createPunishment = CreatePunishment(this)
    val getHistoryPunishments = GetHistoryPunishments(this)
    val getActivePunishments = GetActivePunishments(this)
    val revokePunishment = RevokePunishment(this)
}
