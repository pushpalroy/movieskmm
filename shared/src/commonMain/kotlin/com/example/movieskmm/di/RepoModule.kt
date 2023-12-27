package com.example.movieskmm.di

import com.example.movieskmm.data.network.repository.DefaultMoviesRepo
import com.example.movieskmm.domain.movie.MoviesRepo
import org.koin.dsl.module

fun getRepoModule() = module {
    single<MoviesRepo> {
        DefaultMoviesRepo(moviesService = get())
    }
}
