package ralcoll.matchers

import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import org.junit.jupiter.api.Test

class FirstTest {

    @Test
    fun `Success test`() {
        fun Result<*, *>.successful(): Boolean = when (this) {
            is Success<*> -> true
            is Failure<*> -> false
        }
        val isSuccess = Matcher(Result<*, *>::successful)

        assertThat(Success(1), isSuccess)
    }
}