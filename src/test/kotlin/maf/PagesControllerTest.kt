package maf

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PagesControllerTest {

    val searchService: SearchService = mock()

    @Test
    fun shouldRenderSearchForm() {
        val template = PagesController(searchService).index()

        assertThat(template).isEqualTo("index")
    }

    @Test
    fun shouldRenderLyrics() {
        val template = PagesController(searchService).search("Oasis", "Wanderwall")

        verify(searchService).search("Oasis", "Wanderwall")
        assertThat(template).isEqualTo("lyric")
    }
}