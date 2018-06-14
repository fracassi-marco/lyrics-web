package maf

interface SearchService {
    fun search(author: String, title: String): String
}
