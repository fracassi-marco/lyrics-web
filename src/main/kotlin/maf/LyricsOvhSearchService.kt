package maf

import org.springframework.web.client.RestOperations

class LyricsOvhSearchService(val httpClient: RestOperations, val host: String) : SearchService {
    override fun search(author: String, title: String): String {
        val response = httpClient.postForEntity("$host/v1/$author/$title", Unit::class.java, SearchResponse::class.java)
        return response.body.lyrics
    }
}

data class SearchResponse(val lyrics: String) {
}
