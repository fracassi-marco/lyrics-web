package maf

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PagesController(val searchService: SearchService) {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/search")
    fun search(@RequestParam inputAuthor: String, @RequestParam inputTitle: String): String {
        searchService.search(inputAuthor, inputTitle)
        return "lyric"
    }

}
