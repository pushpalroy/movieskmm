package com.example.movieskmm.features.fileDownload

sealed class FileUiState {
    data class Success(val pdfData: ByteArray, val base64EncodedPdfData: String) : FileUiState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as Success

            return pdfData.contentEquals(other.pdfData)
        }

        override fun hashCode(): Int {
            return pdfData.contentHashCode()
        }
    }

    data class Error(val exceptionMessage: String?) : FileUiState()
    data object Loading : FileUiState()
}