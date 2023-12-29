package com.example.movieskmm.domain.model

data class MoviesList(
    val page: Int,
    val results: List<MovieItem>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieItem(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val releaseDate: String
)