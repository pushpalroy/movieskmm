package com.example.movieskmm.android.di

import com.example.movieskmm.features.movieDetails.MovieDetailsViewModel
import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import com.example.movieskmm.features.popularMovies.PopularViewModel
import com.example.movieskmm.features.topRatedMovies.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NowPlayingViewModel() }
    viewModel { TopRatedViewModel() }
    viewModel { PopularViewModel() }
    viewModel { MovieDetailsViewModel() }
}
