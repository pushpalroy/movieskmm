package com.example.movieskmm.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val releaseDate: String,
    val budget: Long,
    val revenue: Long,
    val tagline: String,
)

fun MovieDetails.asMovieItem(): MovieItem {
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