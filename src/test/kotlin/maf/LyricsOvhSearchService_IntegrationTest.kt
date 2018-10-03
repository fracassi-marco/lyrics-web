package maf

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class LyricsOvhSearchService_IntegrationTest {

    @Test
    fun shouldMakeSearch() {
        val result = LyricsOvhSearchService(Beans().httpClient()).search("U2", "Pride")

        assertThat(result).contains("in the name of love")
    }
}