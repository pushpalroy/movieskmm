package com.example.movieskmm.data.network.sources

import com.example.movieskmm.domain.util.NetworkResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoviesSourceTest {
    @Test
    fun `fetch now playing movies - success`() = runTest {

        // Arrange
        val moviesSource = MoviesSourceImpl(getMockHttpClient(mockSuccessEngine))

        // Act
        val result = moviesSource.fetchNowPlayingMovies()

        // Assert
        assertTrue(result is NetworkResponse.Success)

        val firstMovieItem = result.data.results.first().title
        assertEquals("Napoleon", firstMovieItem)

        val totalResults = result.data.totalResults
        assertEquals(2, totalResults)
    }

    @Test
    fun `fetch now playing movies - failure`() = runTest {

        // Arrange
        val moviesSource = MoviesSourceImpl(getMockHttpClient(mockFailureEngine))

        // Act
        val result = moviesSource.fetchNowPlayingMovies()

        // Assert
        assertTrue(result is NetworkResponse.Failure)
    }
}