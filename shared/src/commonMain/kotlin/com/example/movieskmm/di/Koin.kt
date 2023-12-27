package com.example.movieskmm.di

import com.example.movieskmm.data.network.di.httpClientModule
import com.example.movieskmm.data.network.repository.DefaultMoviesRepo
import com.example.movieskmm.data.network.service.DefaultMoviesService
import com.example.movieskmm.data.network.service.MoviesService
import com.example.movieskmm.domain.movie.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.movie.MoviesRepo
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration()

        modules(
            httpClientModule,
            serviceModule,
            repoModule,
            useCaseModule,
            viewModelModule
        )
    }

val repoModule = module {
    single<MoviesRepo> {
        DefaultMoviesRepo(moviesService = get())
    }
}

val serviceModule = module {
    single<MoviesService> {
        DefaultMoviesService(httpClient = get())
    }
}

val useCaseModule = module {
    single<GetNowPlayingMoviesUseCase> {
        GetNowPlayingMoviesUseCase(moviesRepo = get())
    }
}