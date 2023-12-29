package com.example.movieskmm.data.network.entity

import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.domain.model.MoviesList
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MoviesListEntity(
    val page: Int = 0,
    val results: List<MovieEntity> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@kotlinx.serialization.Serializable
data class MovieEntity(
    val id: Int,
    val title: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    val voteCount: Int = 0,
    val popularity: Double,
    val releaseDate: String = ""
)

fun MoviesListEntity.asDomain(): MoviesList {
    return MoviesList(
        page = page,
        results = results.map { it.asDomain() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun MovieEntity.asDomain(): MovieItem {
    return MovieItem(
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        releaseDate = releaseDate
    )
}