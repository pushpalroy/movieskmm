package com.example.movieskmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform