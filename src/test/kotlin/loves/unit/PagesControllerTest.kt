package loves.unit

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import loves.PagesController
import loves.SearchService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.ui.Model


class PagesControllerTest {

    private val searchService: SearchService = mock()
    private val model: Model = mock()

    @Test
    fun shouldRenderSearchForm() {
        val template = PagesController(searchService).index()

        assertThat(template).isEqualTo("index")
    }

    @Test
    fun shouldRenderLyrics() {
        whenever(searchService.search("Oasis", "Wanderwall")).thenReturn("Today is gonna be the day")

        val template = PagesController(searchService).search(model, "Oasis", "Wanderwall")

        verify(model).addAttribute("text", "Today is gonna be the day")
        assertThat(template).isEqualTo("lyric")
    }

    @Test
    fun shouldRenderLyricNotFound() {
        val template = PagesController(searchService).lyricNotFound()

        assertThat(template).isEqualTo("lyricNotFoud")
    }
}