package com.example.movieskmm.domain.repo

import com.example.movieskmm.domain.util.NetworkResponse

interface FileDownloadRepo {
    suspend fun downloadPdfFile(): NetworkResponse<ByteArray>
}