package com.example.local

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform