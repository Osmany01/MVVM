package com.example.mvvm.ui.popularmovies

import com.example.mvvm.CoroutineTestRule
import com.example.mvvm.data.domain.FakeLocalDataSource
import com.example.mvvm.data.domain.FakeRemoteDataSource
import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.domain.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun `Listening to movies Flow emits the list of movies from the server`() = runBlockingTest {
        val repository = MoviesRepository(FakeLocalDataSource(), FakeRemoteDataSource(fakeMovies))
        val vm = MainActivityViewModel(repository)

        vm.movies.collect {
            Assert.assertEquals(fakeMovies, it)
        }
    }

    private val fakeMovies = listOf(
        MovieModel(1, "Title 1", "poster1"),
        MovieModel(2, "Title 2", "poster2"),
        MovieModel(3, "Title 3", "poster3"),
        MovieModel(4, "Title 4", "poster4")
    )
}