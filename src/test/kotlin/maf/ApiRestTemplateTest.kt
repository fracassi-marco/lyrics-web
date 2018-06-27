package maf

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.util.UriComponentsBuilder


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiRestTemplateTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @MockBean
    private lateinit var searchService : SearchService

    @Test
    fun shouldRenderHomePage() {
        val response = restTemplate.exchange<String>("/", HttpMethod.GET)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun shouldSearchLyrics() {
        whenever(searchService.search("", "")).thenReturn("any")

        val url = UriComponentsBuilder
                .fromPath("/search")
                .queryParam("inputAuthor", "")
                .queryParam("inputTitle", "")

        val response = restTemplate.exchange<String>(url.toUriString(), HttpMethod.GET)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun shouldShowLyricsNotFound() {
        whenever(searchService.search("", "")).thenThrow(LyricNotFoundException())

        val url = UriComponentsBuilder
                .fromPath("/search")
                .queryParam("inputAuthor", "")
                .queryParam("inputTitle", "")

        val response = restTemplate.exchange<String>(url.toUriString(), HttpMethod.GET)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}