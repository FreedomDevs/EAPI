package lib.v2

import lib.core.ApiContext
import lib.v2.auth.AuthModule
import lib.v2.dox.DoxModule

class API(baseUrl: String, token: String) {

    private val context = ApiContext(baseUrl, token)

    val auth = AuthModule(this.context)

    val dox = DoxModule(this.context)
}