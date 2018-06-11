package maf

import freemarker.template.Configuration
import freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS
import freemarker.template.Version
import org.assertj.core.api.Assertions
import org.junit.Test
import java.io.File
import java.io.StringWriter
import java.util.*

class IndexTemplateTest {

    @Test
    fun testProcessTemplateWithModel() {
        val page = contentOf("index", HashMap())

        assertElementExists("input-author", page)
        assertElementExists("input-title", page)
        assertElementExists("btn-search", page)
    }

    private fun contentOf(name: String, model: HashMap<String, Any>): String {
        val configuration = Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS)
        configuration.setDirectoryForTemplateLoading(File("src/main/resources/templates"))
        val template = configuration.getTemplate(name + ".ftl")

        val writer = StringWriter()
        template.process(model, writer)

        val page = writer.toString()
        return page
    }

    private fun assertElementExists(id: String, page: String) {
        Assertions.assertThat(page).contains("id=\"$id\"")
    }
}