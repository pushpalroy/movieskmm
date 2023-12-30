package com.example.movieskmm.util

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import com.example.movieskmm.features.topRatedMovies.TopRatedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class KoinHelper : KoinComponent {
    fun getNowPlayingViewModel() = get<NowPlayingViewModel>()

    fun getTopRatedViewModel() = get<TopRatedViewModel>()
}
