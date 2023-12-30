package com.example.movieskmm.di

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import com.example.movieskmm.features.topRatedMovies.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModel {
        NowPlayingViewModel(getNowPlayingMoviesUseCase = get())
    }
    viewModel {
        TopRatedViewModel(getTopRatedMoviesUseCase = get())
    }
}
