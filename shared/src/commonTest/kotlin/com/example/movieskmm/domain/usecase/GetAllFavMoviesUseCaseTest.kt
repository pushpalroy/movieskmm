package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.domain.repo.MoviesRepo
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coVerify
import io.mockative.every
import io.mockative.mock
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetAllFavMoviesUseCaseTest {

    @Mock
    val moviesRepo = mock(classOf<MoviesRepo>())

    private val fakeMoviesList = listOf(
        MovieItem(
            1,
            "Movie Title 1",
            "Original Title 1",
            "Overview of Movie 1",
            "/backdropPath1.jpg",
            "/posterPath1.jpg",
            8.5,
            120,
            300.5,
            "2021-01-01"
        ),
        MovieItem(
            2,
            "Movie Title 2",
            "Original Title 2",
            "Overview of Movie 2",
            "/backdropPath2.jpg",
            "/posterPath2.jpg",
            7.3,
            95,
            200.0,
            "2021-02-01"
        ),
        MovieItem(
            3,
            "Movie Title 3",
            "Original Title 3",
            "Overview of Movie 3",
            "/backdropPath3.jpg",
            "/posterPath3.jpg",
            6.8,
            80,
            150.2,
            "2021-03-01"
        ),
        MovieItem(
            4,
            "Movie Title 4",
            "Original Title 4",
            "Overview of Movie 4",
            "/backdropPath4.jpg",
            "/posterPath4.jpg",
            7.9,
            110,
            220.3,
            "2021-04-01"
        ),
        MovieItem(
            5,
            "Movie Title 5",
            "Original Title 5",
            "Overview of Movie 5",
            "/backdropPath5.jpg",
            "/posterPath5.jpg",
            6.2,
            70,
            180.4,
            "2021-05-01"
        )
    )

    private val getAllFavMoviesUseCase = GetAllFavMoviesUseCase(moviesRepo)

    @Test
    fun `get all favourite movies successfully`() = runTest {
        // Arrange
        every { moviesRepo.getAllFavouriteMovies() }.returns(flowOf(fakeMoviesList))

        // Act
        val result = getAllFavMoviesUseCase.perform().toList()

        // Assert
        assertEquals(listOf(fakeMoviesList), result)
        coVerify { moviesRepo.getAllFavouriteMovies() }
    }

    @Test
    fun `handle exception when retrieving favourite movies`() = runTest {
        // Arrange
        every { moviesRepo.getAllFavouriteMovies() }.throws(Exception("Test Exception"))

        // Act
        val result = getAllFavMoviesUseCase.perform().toList()

        // Assert
        assertTrue(result.first().isEmpty())
        coVerify { moviesRepo.getAllFavouriteMovies() }
    }
}