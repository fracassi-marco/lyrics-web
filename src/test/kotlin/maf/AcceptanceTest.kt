package maf

import org.assertj.core.api.Assumptions.assumeThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.phantomjs.PhantomJSDriver

class AcceptanceTest {

    private lateinit var _browser: PhantomJSDriver
    private lateinit var _homePage: HomePage
    private var _application = Application()

    @Before
    fun before() {
        _application.start()
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, 8080)
    }

    @After
    fun after() {
        _browser.close()
        _application.stop()
    }

    @Test
    fun shouldFindALyrics() {
        _homePage.open()
        _homePage.searchFor("Aqua", "Barbie Girl")
        
        assumeThat(_browser.pageSource).containsIgnoringCase("You can brush my hair")
    }
}