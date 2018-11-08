package loves.acceptance

import loves.LYRICSOVH_ENDPOINT_PROPERTY
import org.assertj.core.api.Assumptions.assumeThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocalAcceptanceTest {

    @LocalServerPort
    private var _port: Int = 0
    private lateinit var _browser: PhantomJSDriver
    private lateinit var _lyricsOvh: LyricsOvh
    private lateinit var _lyricsOvhServer: ApplicationServer
    private lateinit var _homePage: HomePage

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "http://localhost:9091")
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, _port)
        _lyricsOvh = LyricsOvh()
        _lyricsOvhServer = ApplicationServer(9091, _lyricsOvh).start()
    }

    @After
    fun after() {
        _browser.close()
        _lyricsOvhServer.close()
        System.clearProperty(LYRICSOVH_ENDPOINT_PROPERTY)
    }

    @Test
    fun shouldFindALyrics() {
        _lyricsOvh.calledWith("Aqua", "Barbie Girl").thenReturn("You can brush my hair")

        _homePage.open()
        _homePage.searchFor("Aqua", "Barbie Girl")
        
        assumeThat(_browser.pageSource).contains("You can brush my hair")
    }
}