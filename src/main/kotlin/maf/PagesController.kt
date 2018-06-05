package maf

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PagesController {

    @RequestMapping("/")
    fun index(): String {
        return "form"
    }

}