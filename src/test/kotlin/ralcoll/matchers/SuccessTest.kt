package ralcoll.matchers

import com.natpryce.hamkrest.*
import com.natpryce.hamkrest.assertion.assertThat
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import org.junit.jupiter.api.Test

class SuccessTest {

    private fun <T> isSuccess(): Matcher<Result<T, *>> = object : Matcher<Result<T, *>> {
        override fun invoke(actual: Result<T, *>): MatchResult = when (actual) {
            is Success<T> -> MatchResult.Match
            else -> MatchResult.Mismatch("was: $actual")
        }

        override val description: String
            get() = "is success"
    }

    private fun <T> isSuccess(expectedValue: T) = equalTo(Success(expectedValue))

    private fun <T> isSuccess(matcher: Matcher<T>): Matcher<Result<T, *>> = object : Matcher<Result<T, *>> {
        override fun invoke(actual: Result<T, *>): MatchResult = when (actual) {
            is Success<T> -> matcher.invoke(actual.value)
            else -> MatchResult.Mismatch("was: $actual")
        }

        override val description: String
            get() = "is success & ${matcher.description}"
    }

    @Test
    fun `Success test`() {
        assertThat(Success(1), isSuccess())
    }

    @Test
    fun `Success test 2`() {
        assertThat(Success(1), isSuccess(1))
    }


    @Test
    fun `Success test 3`() {
        assertThat(Success(1), isSuccess(greaterThan(0)))
    }
}