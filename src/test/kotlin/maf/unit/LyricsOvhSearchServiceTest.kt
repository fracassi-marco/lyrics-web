package maf.unit

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import maf.LYRICSOVH_ENDPOINT_PROPERTY
import maf.LyricsOvhSearchService
import maf.SearchResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.web.client.RestOperations

class LyricsOvhSearchServiceTest {

    private val httpClient: RestOperations = mock()

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "http://any.url")
    }

    @After
    fun after() {
        System.clearProperty(LYRICSOVH_ENDPOINT_PROPERTY)
    }

    @Test
    fun shouldMakeSearch() {

        whenever(httpClient.getForObject("http://any.url/v1/U2/Pride", SearchResponse::class.java))
                .thenReturn(SearchResponse("text"))

        val result = LyricsOvhSearchService(httpClient).search("U2", "Pride")

        assertThat(result).isEqualTo("text")
    }
}