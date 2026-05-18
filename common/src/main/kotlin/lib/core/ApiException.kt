package lib.core

class ApiException(
    val status: Int,
    message: String
) : RuntimeException(message)