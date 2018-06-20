package maf

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.client.RestOperations

class LyricsOvhSearchService(val httpClient: RestOperations, val host: String) : SearchService {
    override fun search(author: String, title: String): String {
        return httpClient.getForObject("$host/v1/$author/$title", SearchResponse::class.java)!!.lyrics
    }
}


data class SearchResponse(@JsonProperty("lyrics") val lyrics: String) {
}
