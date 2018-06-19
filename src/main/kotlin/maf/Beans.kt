package maf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
open class Beans {

    @Bean
    open fun httpClient() : RestOperations {
        return RestTemplate()
    }

    @Bean
    open fun searchService(httpClient: RestOperations) : SearchService {
        return LyricsOvhSearchService(httpClient, "https://api.lyrics.ovh")
    }
}