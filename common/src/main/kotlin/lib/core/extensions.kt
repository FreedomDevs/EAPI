package dev.elysium.eapi.lib.core

import java.util.UUID

fun EUUID.toUUID(): UUID = value

fun EUUID.toStringValue(): String = value.toString()

fun UUID.toEUUID(): EUUID = EUUID(this)

fun String.toEUUID(): EUUID = EUUID.fromString(this)

fun String.toEUUIDOrNull(): EUUID? {
    return try {
        EUUID.fromString(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun EUUID.equalsUUID(uuid: UUID): Boolean = value == uuid

fun EUUID.equalsString(uuid: String): Boolean {
    return try {
        value == UUID.fromString(uuid)
    } catch (e: IllegalArgumentException) {
        false
    }
}