package com.example.movieskmm.di

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun getViewModelByPlatform() = module {
    viewModel {
        NowPlayingViewModel(getNowPlayingMoviesUseCase = get())
    }
}
