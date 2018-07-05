package maf

interface PageTemplate {
    fun contentOf(name: String, model: Map<String, Any>): String
}