package maf

import org.assertj.core.api.Assumptions.assumeThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class LocalAcceptanceTest {

    private lateinit var _browser: PhantomJSDriver
    private lateinit var _lyricsOvh: LyricsOvh
    private lateinit var _lyricsOvhServer: ApplicationServer
    private lateinit var _homePage: HomePage
    private val _application = Application()

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "http://localhost:9091")
        _application.start()
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, 8080)
        _lyricsOvh = LyricsOvh()
        _lyricsOvhServer = ApplicationServer(9091, _lyricsOvh).start()
    }

    @After
    fun after() {
        _browser.close()
        _lyricsOvhServer.close()
        _application.stop()
        System.clearProperty(LYRICSOVH_ENDPOINT_PROPERTY)
    }

    @Test
    fun shouldFindALyrics() {
        _lyricsOvh.calledWith("Aqua", "Barbie Girl").thanReturn("You can brush my hair")

        _homePage.open()
        _homePage.searchFor("Aqua", "Barbie Girl")
        
        assumeThat(_browser.pageSource).contains("You can brush my hair")
    }
}