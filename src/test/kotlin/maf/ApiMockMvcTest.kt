package maf

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.util.UriComponentsBuilder


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApiMockMvcTest {

    @Autowired
    private lateinit var mvc : MockMvc

    @MockBean
    private lateinit var searchService : SearchService

    @Test
    fun shouldRenderHomePage() {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    fun shouldSearchLyrics() {
        whenever(searchService.search("", "")).thenReturn("any")

        val url = UriComponentsBuilder
                .fromPath("/search")
                .queryParam("inputAuthor", "")
                .queryParam("inputTitle", "")

        mvc.perform(get(url.toUriString())).andExpect(status().isOk());
    }

    @Test
    fun shouldHandleLyricsNotFound() {
        whenever(searchService.search("", "")).thenThrow(LyricNotFoundException())

        val url = UriComponentsBuilder
                .fromPath("/search")
                .queryParam("inputAuthor", "")
                .queryParam("inputTitle", "")

        mvc.perform(get(url.toUriString())).andExpect(status().isOk());
    }
}