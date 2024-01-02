package com.example.movieskmm.features.fileDownload

import com.example.movieskmm.domain.usecase.FileDownloadUseCase
import com.example.movieskmm.domain.util.NetworkResponse
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

open class FileDownloadViewModel : KMMViewModel(), KoinComponent {

    private val fileDownloadUseCase: FileDownloadUseCase by inject()

    private val _uiState = MutableStateFlow<FileUiState>(FileUiState.Loading)

    @NativeCoroutinesState
    val uiState: StateFlow<FileUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FileUiState.Loading
    )

    @OptIn(ExperimentalEncodingApi::class)
    fun downloadPdfFile() {
        _uiState.value = FileUiState.Loading
        viewModelScope.coroutineScope.launch {
            try {
                when (val response = fileDownloadUseCase.perform()) {
                    is NetworkResponse.Success -> {
                        _uiState.value = FileUiState.Success(
                            pdfData = response.data,
                            base64EncodedPdfData = Base64.encode(response.data)
                        )
                    }

                    is NetworkResponse.Failure -> {
                        _uiState.value =
                            FileUiState.Error(exceptionMessage = response.throwable.message)
                    }

                    is NetworkResponse.Unauthorized -> {
                        _uiState.value =
                            FileUiState.Error(exceptionMessage = response.throwable.message)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = FileUiState.Error(exceptionMessage = e.message.orEmpty())
            }
        }
    }

    override fun onCleared() {
        viewModelScope.coroutineScope.cancel()
        super.onCleared()
    }
}