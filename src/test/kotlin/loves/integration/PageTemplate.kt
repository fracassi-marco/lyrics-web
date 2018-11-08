package loves.integration

import freemarker.template.Configuration
import java.io.File
import java.io.StringWriter
import java.util.HashMap

class PageTemplate {

    fun contentOf(name: String, model: HashMap<String, Any>): String {
        val configuration = Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS)
        configuration.setDirectoryForTemplateLoading(File("src/main/resources/templates"))
        val template = configuration.getTemplate(name + ".ftl")

        val writer = StringWriter()
        template.process(model, writer)

        return writer.toString()
    }
}
