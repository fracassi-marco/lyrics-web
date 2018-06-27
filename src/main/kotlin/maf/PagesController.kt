package maf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PagesController(val searchService: SearchService) {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/search")
    fun search(model: Model, @RequestParam inputAuthor: String, @RequestParam inputTitle: String): String {
        val text = searchService.search(inputAuthor, inputTitle)
        model.addAttribute("text", text)
        return "lyric"
    }

    @ExceptionHandler(LyricNotFoundException::class)
    fun lyricNotFound(): String {
        return "lyricNotFoud"
    }
}
