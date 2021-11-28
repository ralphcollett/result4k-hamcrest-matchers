package com.ralphcollett.matchers

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.greaterThan
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class DescriptionTest {

    @Test
    fun `Success description`() {
        assertEquals("is success", isSuccess().description)
    }

    @Test
    fun `Success mismatch description`() {
        assertMismatchDescription(isSuccess().invoke(Failure(12)), "was: Failure(reason=12)")
    }

    @Test
    fun `Success with value description`() {
        assertEquals("is success & is equal to 12", isSuccess(12).description)
    }

    @Test
    fun `Success with value mismatch description`() {
        assertMismatchDescription(isSuccess(10).invoke(Failure(12)), "was: Failure(reason=12)")
    }

    @Test
    fun `Success with matcher description`() {
        assertEquals("is success & is greater than 10", isSuccess(greaterThan(10)).description)
    }

    @Test
    fun `Success with matcher mismatch description`() {
        assertMismatchDescription(isSuccess(greaterThan(10)).invoke(Failure(12)), "was: Failure(reason=12)")
    }

    @Test
    fun `Failure description`() {
        assertEquals("is failure", isFailure().description)
    }

    @Test
    fun `Failure mismatch description`() {
        assertMismatchDescription(isFailure().invoke(Success(12)), "was: Success(value=12)")
    }

    @Test
    fun `Failure with value description`() {
        assertEquals("is failure & is equal to 12", isFailure(12).description)
    }

    @Test
    fun `Failure with value mismatch description`() {
        assertMismatchDescription(isFailure(10).invoke(Success(12)), "was: Success(value=12)")
    }

    @Test
    fun `Failure with matcher description`() {
        assertEquals("is failure & is greater than 12", isFailure(greaterThan(12)).description)
    }

    @Test
    fun `Failure with matcher mismatch description`() {
        assertMismatchDescription(isFailure(greaterThan(10)).invoke(Success(12)), "was: Success(value=12)")
    }

    private fun assertMismatchDescription(
        matchResult: MatchResult,
        expectedDescription: String
    ) {
        when (matchResult) {
            MatchResult.Match -> fail("expected Mismatch result")
            is MatchResult.Mismatch -> assertEquals(expectedDescription, matchResult.description)
        }
    }
}