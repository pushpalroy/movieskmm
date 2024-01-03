package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.domain.repo.MoviesRepo
import io.github.aakira.napier.Napier

class AddMovieToFavUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<Unit, MovieItem> {
    override suspend fun perform(params: MovieItem) {
        try {
            moviesRepo.addMovieToFavourites(movie = params)
        } catch (e: Exception) {
            Napier.e("Exception in adding movie to favourites: ${e.message}")
        }
    }
}