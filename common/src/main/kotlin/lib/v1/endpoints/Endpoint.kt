package lib.v1.endpoints

import lib.v1.API

interface Endpoint {
    fun inject(api: API)
}