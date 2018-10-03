package maf.integration

import maf.Beans
import maf.LYRICSOVH_ENDPOINT_PROPERTY
import maf.LyricsOvhSearchService
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class LyricsOvhSearchService_SwaggerIntegrationTest {

    @Before
    fun before() {
        System.setProperty(LYRICSOVH_ENDPOINT_PROPERTY, "https://virtserver.swaggerhub.com/fracassi-marco/lyrics-web/1.0.0")
    }

    @After
    fun after() {
        System.clearProperty(LYRICSOVH_ENDPOINT_PROPERTY)
    }

    @Test
    fun shouldMakeSearch() {
        val result = LyricsOvhSearchService(Beans().httpClient()).search("U2", "Pride")

        assertThat(result).contains("in the name of love")
    }
}