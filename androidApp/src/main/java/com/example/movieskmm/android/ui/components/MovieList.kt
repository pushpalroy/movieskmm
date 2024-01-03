package com.example.movieskmm.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieskmm.domain.model.MovieItem

@Composable
internal fun MovieList(
    modifier: Modifier = Modifier,
    listItems: List<MovieItem>,
    onClick: (id: Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 0.dp),
        content = {
            items(listItems) { movieItem ->
                Column(
                    modifier = Modifier.padding(
                        start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp
                    )
                ) {
                    MovieItem(
                        modifier = Modifier,
                        movieItem = movieItem,
                        onClick = onClick
                    )
                }
            }
        }
    )
}