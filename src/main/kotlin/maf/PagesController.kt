package maf

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class PagesController {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

}