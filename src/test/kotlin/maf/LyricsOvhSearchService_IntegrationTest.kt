package maf

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.springframework.web.client.HttpClientErrorException

class LyricsOvhSearchService_IntegrationTest {

    @Test
    fun shouldMakeSearch() {
        val result = LyricsOvhSearchService(Beans().httpClient()).search("U2", "Pride")

        assertThat(result).contains("in the name of love")
    }

    @Test
    fun shouldHandleLyricsNotFound() {
        assertThatThrownBy { LyricsOvhSearchService(Beans().httpClient()).search("U2", "Not existing") }
                .isInstanceOf(HttpClientErrorException::class.java)
                .hasMessageContaining("404 Not Found")
    }
}