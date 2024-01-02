package com.example.movieskmm.data.network.sources

import com.example.movieskmm.domain.util.NetworkResponse

interface FileDownloadSource {
    suspend fun downloadPdfFile(): NetworkResponse<ByteArray>
}