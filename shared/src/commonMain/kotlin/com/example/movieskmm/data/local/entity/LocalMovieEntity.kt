package com.example.movieskmm.data.local.entity

import com.example.movieskmm.domain.model.MovieItem
import data.local.db.Favourite_movie

@kotlinx.serialization.Serializable
data class LocalMovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
)

fun Favourite_movie.asDomain(): MovieItem {
    return MovieItem(
        id = id.toInt(),
        title = title ?: "",
        originalTitle = "",
        overview = overview ?: "",
        backdropPath = backdropPath ?: "",
        posterPath = posterPath ?: "",
        voteAverage = voteAverage?.toDouble() ?: 0.0,
        voteCount = 0,
        popularity = 0.0,
        releaseDate = ""
    )
}