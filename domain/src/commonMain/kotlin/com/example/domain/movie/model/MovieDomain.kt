package com.example.domain.movie.model

data class MoviesListDomain(
    val page: Int,
    val results: List<MovieDomain>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieDomain(
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