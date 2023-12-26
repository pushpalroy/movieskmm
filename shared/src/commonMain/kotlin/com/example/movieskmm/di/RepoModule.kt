package com.example.movieskmm.di

import com.example.domain.movie.MoviesRepo
import com.example.network.repository.DefaultMoviesRepo
import org.koin.dsl.module

fun getRepoModule() = module {
    single<MoviesRepo> {
        DefaultMoviesRepo(moviesService = get())
    }
}
