package maf

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
class AcceptanceTest {

    @LocalServerPort
    var _port: Int = 0
    private lateinit var _browser: PhantomJSDriver

    @Before
    fun before() {
        _browser = PhantomJSDriver()
    }

    @After
    fun after() {
        _browser.close()
    }

    @Test
    fun shouldFindALyrics() {
        openHomePage()

        val page = searchFor("Aqua", "Barbie Girl")

        assertThat(page).contains("I'm a Barbie girl, in a Barbie world")
    }

    private fun searchFor(author: String, title: String): String {
        _browser.findElementById("input-author").sendKeys(author)
        _browser.findElementById("input-title").sendKeys(title)
        _browser.findElementById("btn-search").click()
        return _browser.pageSource;
    }

    private fun openHomePage() {
        _browser.get("http://localhost:$_port/")
    }
}