package lib

class API(internal val baseUrl: String, internal val token: String) {
    @Deprecated(
        message = "v1 is deprecated. Use api.v2 instead.",
        replaceWith = ReplaceWith("v2")
    )
    val v1 = lib.v1.API(baseUrl, token)

    @Deprecated(
        message = "v1 is deprecated. Use api.v2 instead.",
        replaceWith = ReplaceWith("v2")
    )
    fun v1() = v1

    /**
     * Current stable API version.
     */
    val v2 = lib.v2.API(baseUrl, token)

    @Deprecated(
        message = "Use property access api.v2 instead of function call",
        replaceWith = ReplaceWith("v2")
    )
    fun v2() = v2
}