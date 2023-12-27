package com.example.movieskmm.di

import com.example.movieskmm.domain.movie.GetNowPlayingMoviesUseCase
import org.koin.dsl.module

fun getUseCaseModule() = module {
    single<GetNowPlayingMoviesUseCase> {
        GetNowPlayingMoviesUseCase(moviesRepo = get())
    }
}
