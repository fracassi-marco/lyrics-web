package maf.integration

import maf.LyricsOvhSearchService
import maf.SearchService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBootTest()
class BeansTest {

    @Autowired
    private lateinit var searchService: SearchService

    @Autowired
    private lateinit var httpClient: RestOperations

    @Test
    fun shouldUseALyricsOvhSearchService() {
        assertThat(searchService).isInstanceOf(LyricsOvhSearchService::class.java)
    }

    @Test
    fun shouldUseRestTemplate() {
        assertThat(httpClient).isInstanceOf(RestTemplate::class.java)
    }
}