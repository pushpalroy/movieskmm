package com.example.movieskmm.android.ui.navigation.destination

import com.example.movieskmm.android.R

object NowPlayingDestination : TopLevelDestination {
    override val route: String = "now_playing"
    override val destination: String = "now_playing"
    override val selectedIcon: Int = R.drawable.ic_now_playing_selected
    override val unselectedIcon: Int = R.drawable.ic_now_playing_unselected
    override val iconTextId: Int = R.string.now_playing
    override val titleTextId: Int = R.string.now_playing
}

object TopRatedDestination : TopLevelDestination {
    override val route = "top_rated"
    override val destination = "top_rated"
    override val selectedIcon: Int = R.drawable.ic_top_rated_selected
    override val unselectedIcon: Int = R.drawable.ic_top_rated_unselected
    override val iconTextId: Int = R.string.top_rated
    override val titleTextId: Int = R.string.top_rated
}

object PopularDestination : TopLevelDestination {
    override val route = "popular"
    override val destination = "popular"
    override val selectedIcon: Int = R.drawable.ic_popular_selected
    override val unselectedIcon: Int = R.drawable.ic_popular_unselected
    override val iconTextId: Int = R.string.popular
    override val titleTextId: Int = R.string.popular
}

object FavouritesDestination : TopLevelDestination {
    override val route = "favourites"
    override val destination = "favourites"
    override val selectedIcon: Int = R.drawable.ic_favourite_selected
    override val unselectedIcon: Int = R.drawable.ic_favourite_unselected
    override val iconTextId: Int = R.string.favourites
    override val titleTextId: Int = R.string.favourites
}

object MovieDetailsDestination : MoviesNavDestination {
    override val route = "movie_details"
    override val destination = "movie_details"
}

object PdfViewerDestination : MoviesNavDestination {
    override val route = "pdf_viewer"
    override val destination = "pdf_viewer"
}

const val MOVIE_ID_ARGUMENT = "id"
const val MOVIE_DETAILS_PARAMS = "/{$MOVIE_ID_ARGUMENT}"