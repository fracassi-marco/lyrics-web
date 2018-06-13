package maf

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PagesController {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/search")
    fun search(@RequestParam inputAuthor: String, @RequestParam inputTitle: String): String {
        return "index"
    }

}

class SearchRequest {

}
