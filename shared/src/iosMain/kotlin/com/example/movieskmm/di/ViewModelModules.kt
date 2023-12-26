package com.example.movieskmm.di

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import org.koin.dsl.module

actual fun getViewModelByPlatform() = module {
    single {
        NowPlayingViewModel(getNowPlayingMoviesUseCase = get())
    }
}
