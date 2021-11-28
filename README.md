# result4k-hamkrest-matchers

[Result4k](https://github.com/npryce/result4k/) [Hamkrest](https://github.com/npryce/hamkrest) matchers.

[![Build Status](https://app.travis-ci.com/ralphcollett/result4k-hamkrest-matchers.svg?branch=main)](https://app.travis-ci.com/ralphcollett/result4k-hamkrest-matchers)

## Example usage

```kotlin
val result: Result<Int, String> = TODO()
assertThat(result, isSuccess())
assertThat(result, isSuccess(1))
assertThat(result, isSuccess(greaterThan(0)))
assertThat(result, isFailure())
assertThat(result, isFailure("12345"))
assertThat(result, isFailure(startsWith("123")))
```

Requires imports

```
import com.natpryce.hamkrest.*
import com.natpryce.hamkrest.assertion.assertThat
import dev.forkhandles.result4k.Result
import org.junit.jupiter.api.Test
```