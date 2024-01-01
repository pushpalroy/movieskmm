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