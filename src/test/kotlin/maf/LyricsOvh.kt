package maf

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.jetty.util.URIUtil
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LyricsOvh : HttpServlet() {

    private var _text: String = ""
    private var _urlPattern: String = ".*"

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        assertThat(request.requestURI).matches(_urlPattern)

        response.contentType = "application/json"
        response.writer.write(Gson().toJson(SearchResponse(_text)))
    }

    fun calledWith(author: String, title: String): LyricsOvh {
        _urlPattern = ".*/${URIUtil.encodePath(author)}/${URIUtil.encodePath(title)}"
        return this
    }

    fun thanReturn(text: String): LyricsOvh {
        _text = text
        return this
    }
}
