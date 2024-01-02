package com.example.movieskmm.di

import com.example.movieskmm.data.network.client.httpClientModule
import com.example.movieskmm.data.network.repository.FileDownloadRepoImpl
import com.example.movieskmm.data.network.repository.MoviesRepoImpl
import com.example.movieskmm.data.network.sources.FileDownloadSource
import com.example.movieskmm.data.network.sources.FileDownloadSourceImpl
import com.example.movieskmm.data.network.sources.MoviesSourceImpl
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.usecase.FileDownloadUseCase
import com.example.movieskmm.domain.usecase.GetMovieDetailsByIdUseCase
import com.example.movieskmm.domain.usecase.GetPopularMoviesUseCase
import com.example.movieskmm.domain.usecase.GetTopRatedMoviesUseCase
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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
        )
    }

fun initKoin() = initKoin {}

val serviceModule = module {
    single<MoviesSource> {
        MoviesSourceImpl(httpClient = get(named("movies")))
    }
    single<FileDownloadSource> {
        FileDownloadSourceImpl(httpClient = get(named("fileDownload")))
    }
}

val repoModule = module {
    single<MoviesRepo> {
        MoviesRepoImpl(moviesService = get())
    }
    single<FileDownloadRepo> {
        FileDownloadRepoImpl(fileDownloadService = get())
    }
}

val useCaseModule = module {
    single<GetNowPlayingMoviesUseCase> {
        GetNowPlayingMoviesUseCase(moviesRepo = get())
    }
    single<GetTopRatedMoviesUseCase> {
        GetTopRatedMoviesUseCase(moviesRepo = get())
    }
    single<GetPopularMoviesUseCase> {
        GetPopularMoviesUseCase(moviesRepo = get())
    }
    single<GetMovieDetailsByIdUseCase> {
        GetMovieDetailsByIdUseCase(moviesRepo = get())
    }
    single<FileDownloadUseCase> {
        FileDownloadUseCase(fileDownloadRepo = get())
    }
}