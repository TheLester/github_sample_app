package com.asanarebel.githubapp.functionaltests.rule


/**
 * @see MockWebServerRule
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NeedsMockWebServer(
        /**
         * Optional specifier for a setupMethod that needs to be invoked
         * during initialization of [com.squareup.okhttp.mockwebserver.MockWebServer].
         *
         *
         * Useful for setting up responses that you simply can not define in the test code because app already hit the server.
         *
         * @return empty string if no method invocation required or public method name that needs to be called.
         */
        val setupMethod: String = "")
