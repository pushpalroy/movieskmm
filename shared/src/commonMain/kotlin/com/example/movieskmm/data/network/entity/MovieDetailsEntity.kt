package com.example.movieskmm.data.network.entity

import com.example.movieskmm.domain.model.MovieDetails
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieDetailsEntity(
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
    val releaseDate: String = "",
    val budget: Long,
    val revenue: Long,
    val tagline: String
)

fun MovieDetailsEntity.asDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        releaseDate = releaseDate,
        budget = budget,
        revenue = revenue,
        tagline = tagline
    )
}