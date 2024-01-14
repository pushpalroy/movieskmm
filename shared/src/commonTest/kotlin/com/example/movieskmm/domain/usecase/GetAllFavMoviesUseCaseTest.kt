package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.fakeData.fakeMovieItems
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

    private val getAllFavMoviesUseCase = GetAllFavMoviesUseCase(moviesRepo)

    @Test
    fun `get all favourite movies successfully`() = runTest {
        // Arrange
        every { moviesRepo.getAllFavouriteMovies() }.returns(flowOf(fakeMovieItems))

        // Act
        val result = getAllFavMoviesUseCase.perform().toList()

        // Assert
        assertEquals(listOf(fakeMovieItems), result)
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