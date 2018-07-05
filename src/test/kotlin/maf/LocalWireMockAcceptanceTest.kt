package maf

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import org.assertj.core.api.Assumptions.assumeThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver


class LocalWireMockAcceptanceTest {

    private lateinit var _browser: PhantomJSDriver
    private lateinit var _homePage: HomePage
    private val _application = Application()

    @get:Rule
    val wireMockRule = WireMockRule(9091)

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "http://localhost:9091")
        _application.start()
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, 8080)
    }

    @After
    fun after() {
        _browser.close()
        _application.stop()
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