package com.example.movieskmm.data.repo

import com.example.movieskmm.data.local.sources.MoviesLocalSource
import com.example.movieskmm.data.network.entity.MoviesListEntity
import com.example.movieskmm.data.network.entity.asDomain
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.util.NetworkResponse
import com.example.movieskmm.fakeData.fakeMovieEntities
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoviesRepoTest {

    @Mock
    val moviesNetworkService = mock(classOf<MoviesSource>())

    @Mock
    val moviesLocalService = mock(classOf<MoviesLocalSource>())

    private val moviesRepo = MoviesRepoImpl(moviesNetworkService, moviesLocalService)

    @Test
    fun `fetch now playing movies - success`() = runTest {
        // Arrange
        coEvery { moviesNetworkService.fetchNowPlayingMovies() }.returns(
            NetworkResponse.Success(
                MoviesListEntity(
                    page = 1,
                    results = fakeMovieEntities,
                    totalPages = 1,
                    totalResults = fakeMovieEntities.size
                )
            )
        )

        // Act
        val result = moviesRepo.fetchNowPlayingMovies()

        // Assert
        assertTrue(result is NetworkResponse.Success)

        val firstMovieItem = result.data.results.first()
        assertEquals(fakeMovieEntities.first().asDomain(), firstMovieItem)
    }

    @Test
    fun `fetch now playing movies - failure`() = runTest {
        // Arrange
        coEvery { moviesNetworkService.fetchNowPlayingMovies() }.returns(
            NetworkResponse.Failure(
                Exception("Test Exception")
            )
        )

        // Act
        val result = moviesRepo.fetchNowPlayingMovies()

        // Assert
        assertTrue(result is NetworkResponse.Failure)

        val exceptionMessage = result.throwable.message
        assertEquals("Test Exception", exceptionMessage)
    }
}