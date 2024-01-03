package com.example.movieskmm.di

import com.example.movieskmm.data.local.sources.MoviesLocalSource
import com.example.movieskmm.data.local.sources.MoviesLocalSourceImpl
import com.example.movieskmm.data.network.client.httpClientModule
import com.example.movieskmm.data.repo.FileDownloadRepoImpl
import com.example.movieskmm.data.repo.MoviesRepoImpl
import com.example.movieskmm.data.network.sources.FileDownloadSource
import com.example.movieskmm.data.network.sources.FileDownloadSourceImpl
import com.example.movieskmm.data.network.sources.MoviesSourceImpl
import com.example.movieskmm.data.network.sources.MoviesSource
import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieskmm.domain.repo.MoviesRepo
import com.example.movieskmm.domain.usecase.AddMovieToFavUseCase
import com.example.movieskmm.domain.usecase.FileDownloadUseCase
import com.example.movieskmm.domain.usecase.GetAllFavMoviesUseCase
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
            networkServiceModule,
            localServiceModule,
            repoModule,
            useCaseModule,
            platformModule()
        )
    }

fun initKoin() = initKoin {}

val networkServiceModule = module {
    single<MoviesSource> {
        MoviesSourceImpl(httpClient = get(named("movies")))
    }
    single<FileDownloadSource> {
        FileDownloadSourceImpl(httpClient = get(named("fileDownload")))
    }
}

val localServiceModule = module {
    single<MoviesLocalSource> {
        MoviesLocalSourceImpl(driver = get())
    }
}

val repoModule = module {
    single<MoviesRepo> {
        MoviesRepoImpl(
            moviesNetworkService = get(),
            moviesLocalService = get()
        )
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
    single<AddMovieToFavUseCase> {
        AddMovieToFavUseCase(moviesRepo = get())
    }
    single<GetAllFavMoviesUseCase> {
        GetAllFavMoviesUseCase(moviesRepo = get())
    }
}