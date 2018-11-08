package loves

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.client.RestOperations

const val LYRICSOVH_ENDPOINT_PROPERTY: String = "LYRICSOVH_ENDPOINT_PROPERTY"

class LyricsOvhSearchService(val httpClient: RestOperations) : SearchService {

    override fun search(author: String, title: String): String {
        val endpoint = System.getProperty(LYRICSOVH_ENDPOINT_PROPERTY, "https://api.lyrics.ovh")
        return httpClient.getForObject("$endpoint/v1/$author/$title", SearchResponse::class.java)!!.lyrics
    }
}


data class SearchResponse(@JsonProperty("lyrics") val lyrics: String)
