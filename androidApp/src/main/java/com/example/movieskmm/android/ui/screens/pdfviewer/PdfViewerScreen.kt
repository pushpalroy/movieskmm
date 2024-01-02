package com.example.movieskmm.android.ui.screens.pdfviewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieskmm.android.ui.components.mirroringBackIcon
import com.example.movieskmm.android.ui.designsystem.MoviesAppTheme
import com.example.movieskmm.features.fileDownload.FileDownloadViewModel
import com.example.movieskmm.features.fileDownload.FileUiState
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import io.ktor.util.encodeBase64
import org.koin.androidx.compose.getViewModel

@Composable
fun PdfViewerScreen(
    upPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fileDownloadViewModel: FileDownloadViewModel = getViewModel()
    val uiState by fileDownloadViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        fileDownloadViewModel.downloadPdfFile()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = upPress,
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .size(36.dp)
                .background(
                    color = MoviesAppTheme.colors.uiBackgroundTertiary.copy(alpha = 0.32f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = mirroringBackIcon(),
                tint = MoviesAppTheme.colors.iconPrimaryActive,
                contentDescription = "Back button"
            )
        }
        when (uiState) {
            is FileUiState.Loading -> {
                CircularProgressIndicator()
            }

            is FileUiState.Success -> {
                val pdfData = (uiState as FileUiState.Success).pdfData.encodeBase64()
                val pdfState = rememberVerticalPdfReaderState(
                    resource = ResourceType.Base64(pdfData),
                    isZoomEnable = true,
                )

                VerticalPDFReader(
                    state = pdfState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White),
                )
            }

            is FileUiState.Error -> {
                val errorMessage = (uiState as FileUiState.Error).exceptionMessage
                if (errorMessage.isNullOrEmpty().not()) {
                    Text(text = errorMessage ?: "", color = Color.Red)
                }
            }

            else -> Unit
        }
    }
}
