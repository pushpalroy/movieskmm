package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.model.MovieItem
import com.example.movieskmm.domain.repo.MoviesRepo
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetAllFavMoviesUseCase(
    private val moviesRepo: MoviesRepo
) : UseCase<Flow<List<MovieItem>>, Unit> {
    override suspend fun perform(): Flow<List<MovieItem>> {
        return try {
            moviesRepo.getAllFavouriteMovies()
        } catch (e: Exception) {
            Napier.e("Exception in adding movie to favourites: ${e.message}")
            flowOf(listOf())
        }
    }
}