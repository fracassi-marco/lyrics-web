package maf

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.web.client.RestOperations

class LyricsOvhSearchServiceTest {

    private val httpClient: RestOperations = mock()

    @Test
    fun shouldMakeSearch() {
        whenever(httpClient.getForObject("http://any.url/v1/U2/Pride", SearchResponse::class.java))
                .thenReturn(SearchResponse("text"))

        val result = LyricsOvhSearchService(httpClient, "http://any.url").search("U2", "Pride")

        assertThat(result).isEqualTo("text")
    }
}