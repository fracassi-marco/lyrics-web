package maf

import freemarker.template.Configuration
import java.io.File
import java.io.StringWriter

class FreemarkerPageTemplate : PageTemplate {

    override fun contentOf(name: String, model: Map<String, Any>): String {
        val configuration = Configuration()
        configuration.setDirectoryForTemplateLoading(File("src/main/resources/templates"))
        val template = configuration.getTemplate("$name.ftl")

        val writer = StringWriter()
        template.process(model, writer)

        return writer.toString()
    }
}
