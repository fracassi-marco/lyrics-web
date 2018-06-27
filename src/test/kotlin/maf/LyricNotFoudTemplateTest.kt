package maf

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class LyricNotFoudTemplateTest {

    @Test
    fun shouldRenderMessage() {
        val page = PageTemplate().contentOf("lyricNotFoud", HashMap())

        assertThat(page).contains("Sorry, lyric not found")
    }
}