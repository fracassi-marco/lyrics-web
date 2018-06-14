package maf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class Beans {

    @Bean
    open fun searchService() : SearchService {
        return LyricsOvhSearchService()
    }
}