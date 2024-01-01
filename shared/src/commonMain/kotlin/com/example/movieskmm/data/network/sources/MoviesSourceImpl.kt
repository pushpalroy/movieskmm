package com.example.movieskmm.data.network.sources

import com.example.movieskmm.data.network.entity.MovieDetailsEntity
import com.example.movieskmm.data.network.entity.MoviesListEntity
import com.example.movieskmm.data.util.ApiConstants
import com.example.movieskmm.domain.util.NetworkResponse
import com.example.movieskmm.domain.util.getSafeNetworkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String = ApiConstants.BASE_URL
) : MoviesSource {
    override suspend fun fetchNowPlayingMovies(): NetworkResponse<MoviesListEntity> =
        getSafeNetworkResponse {
            httpClient.get("$baseUrl${ApiConstants.ENDPOINT_NOW_PLAYING_MOVIES}").body()
        }

    override suspend fun fetchPopularMovies(): NetworkResponse<MoviesListEntity> =
        getSafeNetworkResponse {
            httpClient.get("$baseUrl${ApiConstants.ENDPOINT_POPULAR_MOVIES}").body()
        }

    override suspend fun fetchTopRatedMovies(): NetworkResponse<MoviesListEntity> =
        getSafeNetworkResponse {
            httpClient.get("$baseUrl${ApiConstants.ENDPOINT_TOP_RATED_MOVIES}").body()
        }

    override suspend fun fetchMovieDetails(id: Int): NetworkResponse<MovieDetailsEntity> =
        getSafeNetworkResponse {
            httpClient.get("$baseUrl${ApiConstants.ENDPOINT_MOVIE_DETAILS}/$id").body()
        }
}
