package maf

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PagesController(val searchService: SearchService, val pageTemplate: PageTemplate) : HttpServlet() {

    public override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        var template = "index"
        val model = HashMap<String, Any>()
        if(req.requestURI.equals("/search")) {
            try {
                val text = searchService.search(req.getParameter("inputAuthor"), req.getParameter("inputTitle"))
                template = "lyric"
                model.put("text", text)
            }
            catch (e: LyricNotFoundException) {
                template = "lyricNotFoud"
            }
        }
        resp.writer.write(pageTemplate.contentOf(template, model))
    }
}
