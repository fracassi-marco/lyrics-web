package maf

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PagesControllerTest {

    @Test
    fun shouldRenderSearchForm() {
        val template = PagesController().index()

        assertThat(template).isEqualTo("index")
    }
}