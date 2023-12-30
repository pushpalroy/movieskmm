package com.example.movieskmm.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieskmm.data.util.ApiConstants
import com.example.movieskmm.domain.model.MovieItem

@Composable
internal fun MovieList(
    modifier: Modifier = Modifier,
    listItems: List<MovieItem>,
    onclick: (id: Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp),
        content = {
            items(listItems) {
                Column(
                    modifier = Modifier.padding(
                        start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp
                    )
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(ApiConstants.IMAGE_BASE_URL.plus(it.posterPath))
                            .crossfade(300)
                            .build(),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                            .clickable {
                                onclick(it.id)
                            },
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    )
}