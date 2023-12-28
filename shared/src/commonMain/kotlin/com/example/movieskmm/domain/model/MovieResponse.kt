package com.example.movieskmm.domain.model

data class MoviesListResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieResponse(
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