package maf

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class PagesControllerTest {

    private val searchService: SearchService = mock()
    private val request: HttpServletRequest = mock()
    private val response: HttpServletResponse = mock()
    private val pageTemplate: PageTemplate = mock()

    @Before
    fun before() {
        whenever(response.writer).thenReturn(mock())
    }

    @Test
    fun shouldRenderSearchForm() {
        whenever(request.requestURI).thenReturn("/")

        PagesController(searchService, pageTemplate).doGet(request, response)

        verify(pageTemplate).contentOf(eq("index"), any())
    }

    @Test
    fun shouldRenderLyrics() {
        whenever(request.requestURI).thenReturn("/search")
        whenever(request.getParameter("inputAuthor")).thenReturn("Oasis")
        whenever(request.getParameter("inputTitle")).thenReturn("Wonderwall")
        whenever(searchService.search("Oasis", "Wonderwall")).thenReturn("Today is gonna be the day")

        PagesController(searchService, pageTemplate).doGet(request, response)

        verify(pageTemplate).contentOf(eq("lyric"), eq(mapOf("text" to "Today is gonna be the day")))
    }

    @Test
    fun shouldRenderLyricNotFound() {
        whenever(request.requestURI).thenReturn("/search")
        whenever(request.getParameter("inputAuthor")).thenReturn("Oasis")
        whenever(request.getParameter("inputTitle")).thenReturn("Big sky")
        whenever(searchService.search("Oasis", "Big sky")).thenThrow(LyricNotFoundException())

        PagesController(searchService, pageTemplate).doGet(request, response)

        verify(pageTemplate).contentOf(eq("lyricNotFoud"), any())
    }
}