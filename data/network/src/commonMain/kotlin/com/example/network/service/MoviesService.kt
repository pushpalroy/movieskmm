package com.example.network.service

import com.example.network.dto.MoviesListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface MoviesService {
    suspend fun fetchNowPlayingMovies(): MoviesListDto
    suspend fun fetchPopularMovies(): MoviesListDto
    suspend fun fetchTopRatedMovies(): MoviesListDto
}

class DefaultMoviesService(
    private val httpClient: HttpClient,
    private val baseUrl: String = "https://api.themoviedb.org/3"
) : MoviesService {
    override suspend fun fetchNowPlayingMovies(): MoviesListDto {
        return httpClient.get("$baseUrl/movie/now_playing").body()
    }

    override suspend fun fetchPopularMovies(): MoviesListDto {
        return httpClient.get("$baseUrl/movie/popular").body()
    }

    override suspend fun fetchTopRatedMovies(): MoviesListDto {
        return httpClient.get("$baseUrl/movie/top_rated").body()
    }
}
