package dev.elysium.eapi.lib.v1.endpoints

import dev.elysium.eapi.lib.v1.API

interface Endpoint {
    fun inject(api: API)
}