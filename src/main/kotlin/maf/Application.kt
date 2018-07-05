package maf

import org.springframework.web.client.RestTemplate


open class Application() {

    private var server: ApplicationServer

    init {
        server = ApplicationServer(8080, PagesController(LyricsOvhSearchService(RestTemplate()), FreemarkerPageTemplate()))
    }

    fun start() {
        server.start()
    }

    fun stop() {
        server.close();
    }
}

fun main(args: Array<String>) {
    Application().start()
}