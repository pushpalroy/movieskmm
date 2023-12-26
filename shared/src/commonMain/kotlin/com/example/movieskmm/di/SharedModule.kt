package com.example.movieskmm.di

import com.example.network.di.getHttpClientModule

private val sharedModules = listOf(
    getViewModelByPlatform(),
    getUseCaseModule(),
    getRepoModule(),
    getServiceModule(),
    getHttpClientModule()
)

fun getSharedModules() = sharedModules
