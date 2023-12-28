package com.example.movieskmm.di

import com.example.movieskmm.data.network.client.httpClientModule
import com.example.movieskmm.data.network.repository.MoviesRepoImpl
import com.example.movieskmm.data.network.sources.MoviesSourceImpl
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.repo.MoviesRepo
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
        MoviesRepoImpl(moviesService = get())
    }
}

val serviceModule = module {
    single<MoviesSource> {
        MoviesSourceImpl(httpClient = get())
    }
}

val useCaseModule = module {
    single<GetNowPlayingMoviesUseCase> {
        GetNowPlayingMoviesUseCase(moviesRepo = get())
    }
}