package maf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PagesController(val searchService: SearchService) {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/search")
    fun search(model: Model, @RequestParam inputAuthor: String, @RequestParam inputTitle: String): String {
        val text = searchService.search(inputAuthor, inputTitle)
        model.addAttribute("text", text)
        return "lyric"
    }

}
