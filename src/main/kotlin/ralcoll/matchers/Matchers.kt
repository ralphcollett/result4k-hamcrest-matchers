package ralcoll.matchers

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success

fun isSuccess(): Matcher<Result<*, *>> = object : Matcher<Result<*, *>> {
    override fun invoke(actual: Result<*, *>): MatchResult = when (actual) {
        is Success<*> -> MatchResult.Match
        else -> MatchResult.Mismatch("was: $actual")
    }

    override val description: String
        get() = "is success"
}

fun <T> isSuccess(expectedValue: T) = isSuccess(equalTo(expectedValue))

fun <T> isSuccess(matcher: Matcher<T>): Matcher<Result<T, *>> = object : Matcher<Result<T, *>> {
    override fun invoke(actual: Result<T, *>): MatchResult = when (actual) {
        is Success<T> -> matcher.invoke(actual.value)
        else -> MatchResult.Mismatch("was: $actual")
    }

    override val description: String
        get() = "is success & ${matcher.description}"
}

fun isFailure(): Matcher<Result<*, *>> = object : Matcher<Result<*, *>> {
    override fun invoke(actual: Result<*, *>): MatchResult = when (actual) {
        is Failure<*> -> MatchResult.Match
        else -> MatchResult.Mismatch("was: $actual")
    }

    override val description: String
        get() = "is failure"
}

fun <T> isFailure(expectedValue: T) = isFailure(equalTo(expectedValue))

fun <T> isFailure(matcher: Matcher<T>): Matcher<Result<*, T>> = object : Matcher<Result<*, T>> {
    override fun invoke(actual: Result<*, T>): MatchResult = when (actual) {
        is Failure<T> -> matcher.invoke(actual.reason)
        else -> MatchResult.Mismatch("was: $actual")
    }

    override val description: String
        get() = "is failure & ${matcher.description}"
}