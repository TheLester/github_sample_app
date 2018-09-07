package com.asanarebel.githubapp.functionaltests.rule

import androidx.test.InstrumentationRegistry
import com.asanarebel.githubapp.GithubApplication
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * JUnit test rule for mocking web server
 */
class MockWebServerRule(private val testClassInstance: Any) : TestRule {

    override fun apply(base: Statement,description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val needsMockWebServer = description.getAnnotation(NeedsMockWebServer::class.java)

                if (needsMockWebServer != null) {
                    val mockWebServer = MockWebServer()
                    mockWebServer.start()

                    (InstrumentationRegistry.getTargetContext().applicationContext as GithubApplication)
                            .dynamicUrlInterceptor
                            .setSchemeAndHost("http", mockWebServer.url("").toString())

                    if (!needsMockWebServer.setupMethod.isEmpty()) {
                        val setupMethod = testClassInstance.javaClass
                                .getDeclaredMethod(needsMockWebServer.setupMethod, MockWebServer::class.java)
                        setupMethod.invoke(testClassInstance, mockWebServer)
                    }

                    try {
                        base.evaluate()
                    } finally {
                        mockWebServer.shutdown()
                    }
                } else {
                    base.evaluate()
                }
            }
        }
    }
}
