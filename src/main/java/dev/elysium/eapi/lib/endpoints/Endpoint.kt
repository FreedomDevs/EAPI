package dev.elysium.eapi.lib.endpoints

import dev.elysium.eapi.lib.API

interface Endpoint {
    fun inject(api: API)
}