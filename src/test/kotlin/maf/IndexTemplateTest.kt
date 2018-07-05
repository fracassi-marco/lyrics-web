package maf

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class IndexTemplateTest {

    @Test
    fun testProcessTemplateWithModel() {
        val page = FreemarkerPageTemplate().contentOf("index", HashMap())

        assertElementExists("input-author", page)
        assertElementExists("input-title", page)
        assertElementExists("btn-search", page)
        assertThat(page).contains("action=\"/search\"")
    }

    private fun assertElementExists(id: String, page: String) {
        assertThat(page).contains("id=\"$id\"")
    }
}