package com.example.movieskmm.di

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import com.example.movieskmm.features.topRatedMovies.TopRatedViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    single {
        NowPlayingViewModel(getNowPlayingMoviesUseCase = get())
    }

    single {
        TopRatedViewModel(getTopRatedMoviesUseCase = get())
    }
}
