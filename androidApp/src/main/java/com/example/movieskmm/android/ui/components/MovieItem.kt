package com.example.movieskmm.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import com.example.movieskmm.data.util.ApiConstants
import com.example.movieskmm.domain.model.MovieItem

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movieItem: MovieItem,
    onClick: (id: Int) -> Unit,
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ApiConstants.IMAGE_BASE_URL.plus(movieItem.posterPath))
                .crossfade(300)
                .build(),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = modifier
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5))
                .clickable {
                    onClick(movieItem.id)
                },
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MoviesAppTheme.typography.heading1,
            color = MoviesAppTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}