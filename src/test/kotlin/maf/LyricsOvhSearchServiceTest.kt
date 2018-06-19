package maf

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

class LyricsOvhSearchServiceTest {

    private val httpClient: RestOperations = mock()

    @Test
    fun shouldMakeSearch() {
        whenever(httpClient.postForEntity("http://any.url/v1/U2/Pride", Unit::class.java, SearchResponse::class.java))
                .thenReturn(ResponseEntity(SearchResponse("text"), HttpStatus.OK))

        val result = LyricsOvhSearchService(httpClient, "http://any.url").search("U2", "Pride")

        assertThat(result).isEqualTo("text")
    }
}