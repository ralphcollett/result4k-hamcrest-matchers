package ralcoll.matchers

import com.natpryce.hamkrest.*
import com.natpryce.hamkrest.assertion.assertThat
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.Failure
import org.junit.jupiter.api.Test

class MatchersTest {

    @Test
    fun `Success`() {
        assertThat(Success(1), isSuccess())
        assertThat(Failure(1), !isSuccess())
    }

    @Test
    fun `Success with value`() {
        assertThat(Success(1), isSuccess(1))
        assertThat(Success(2), !isSuccess(1))
        assertThat(Failure(1), !isSuccess(1))
    }


    @Test
    fun `Success with matcher`() {
        assertThat(Success(1), isSuccess(greaterThan(0)))
        assertThat(Success(1), !isSuccess(lessThan(0)))
        assertThat(Failure(1), !isSuccess(greaterThan(0)))
    }

    @Test
    fun `Failure`() {
        assertThat(Failure("12345"), isFailure())
        assertThat(Success("12345"), !isFailure())
    }

    @Test
    fun `Failure with value`() {
        assertThat(Failure("12345"), isFailure("12345"))
        assertThat(Failure("56789"), !isFailure("12345"))
        assertThat(Success("12345"), !isFailure("12345"))
    }

    @Test
    fun `Failure with matcher`() {
        assertThat(Failure("12345"), isFailure(startsWith("123")))
        assertThat(Failure("12345"), !isFailure(endsWith("123")))
        assertThat(Success("12345"), !isFailure(endsWith("123")))
    }
}