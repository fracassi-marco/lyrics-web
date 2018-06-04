import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AcceptanceTest {

    @Test
    fun firstOne() {
        assertThat("one").isEqualTo("one")
    }
}