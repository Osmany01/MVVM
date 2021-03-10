package com.example.mvvm.data.domain

import com.example.mvvm.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test(expected = TimeoutCancellationException::class)
    fun `After timeout, an exception is thrown`() = runBlockingTest {
        val repository = MoviesRepository(
            FakeLocalDataSource(),
            FakeRemoteDataSource(delay = 6_000)
        )

        repository.checkRequiredNewPage(0)

        advanceTimeBy(5_000)
    }
}