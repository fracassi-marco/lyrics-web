package maf

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assumptions.assumeThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import org.junit.Rule


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocalWireMockAcceptanceTest {

    @LocalServerPort
    private var _port: Int = 0
    private lateinit var _browser: PhantomJSDriver
    private lateinit var _homePage: HomePage

    @get:Rule
    val wireMockRule = WireMockRule(9091)

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "http://localhost:9091")
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, _port)
    }

    @After
    fun after() {
        _browser.close()
        System.clearProperty(LYRICSOVH_ENDPOINT_PROPERTY)
    }

    @Test
    fun shouldFindALyrics() {
        stubFor(get("/v1/Aqua/Barbie%20Girl")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(Gson().toJson(SearchResponse("You can brush my hair")))));

        _homePage.open()
        _homePage.searchFor("Aqua", "Barbie Girl")
        
        assumeThat(_browser.pageSource).contains("You can brush my hair")
    }
}