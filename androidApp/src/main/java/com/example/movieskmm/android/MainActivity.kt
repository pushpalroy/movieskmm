package com.example.movieskmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieskmm.android.ui.MoviesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MoviesApp()
        }
    }
}
