package com.asanarebel.githubapp.functionaltests.test

import androidx.test.runner.AndroidJUnit4
import com.asanarebel.githubapp.functionaltests.rule.NeedsMockWebServer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReposErrorTest : BaseGithubTest() {

    /**
     * @see .shouldDisplayErrorUiFrom500Response_setupMockWebServer
     */
    @Test
    @NeedsMockWebServer(setupMethod = "shouldDisplayErrorUiFrom500Response_setupMockWebServer")
    fun shouldDisplayErrorUiFrom500Response() {
        baseScreen
                .searchWithQuery("error test")
                .shouldDisplayError(SERVER_ERROR_MESSAGE)
    }

    /**
     * @see .shouldDisplayErrorUiFrom500Response
     */
    fun shouldDisplayErrorUiFrom500Response_setupMockWebServer(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(MockResponse().setStatus("HTTP/1.1 500 Server error").setBody(
                "{\"message\": \"$SERVER_ERROR_MESSAGE\"}"
        ))
    }

    companion object {
        const val SERVER_ERROR_MESSAGE = "Oops! Server is unavailable"
    }

}