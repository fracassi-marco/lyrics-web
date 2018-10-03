package maf.acceptance

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
class AcceptanceTest {

    @LocalServerPort
    private var _port: Int = 0
    private lateinit var _browser: PhantomJSDriver
    private lateinit var _homePage: HomePage

    @Before
    fun before() {
        _browser = PhantomJSDriver()
        _homePage = HomePage(_browser, _port)
    }

    @After
    fun after() {
        _browser.close()
    }

    @Test
    fun shouldFindALyrics() {
        _homePage.open()
        _homePage.searchFor("Aqua", "Barbie Girl")
        
        assumeThat(_browser.pageSource).containsIgnoringCase("You can brush my hair")
    }
}