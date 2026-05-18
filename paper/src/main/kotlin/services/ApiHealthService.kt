package services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lib.API
import java.util.concurrent.atomic.AtomicBoolean

class ApiHealthService(
    private val scope: CoroutineScope,
    private val api: API
) {

    private val _status = AtomicBoolean(true)
    val status: Boolean get() = _status.get()

    fun refreshAsync() {
        scope.launch(Dispatchers.IO) {
            val ok = try {
                api.v1().getHealth.fetch() != null
            } catch (_: Exception) {
                false
            }

            _status.set(ok)
        }
    }
}