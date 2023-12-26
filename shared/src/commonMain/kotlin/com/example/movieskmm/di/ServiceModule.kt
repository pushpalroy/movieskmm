package com.example.movieskmm.di

import com.example.network.service.DefaultMoviesService
import com.example.network.service.MoviesService
import org.koin.dsl.module

fun getServiceModule() = module {
    single<MoviesService> {
        DefaultMoviesService(httpClient = get())
    }
}
