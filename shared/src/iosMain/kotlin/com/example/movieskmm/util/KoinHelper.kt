package com.example.movieskmm.util

import com.example.movieskmm.features.nowPlayingMovies.NowPlayingViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class KoinHelper : KoinComponent {
    fun getNowPlayingViewModel() = get<NowPlayingViewModel>()
}
