package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.sources.FileDownloadSource
import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.util.NetworkResponse

class FileDownloadRepoImpl(
    private val fileDownloadService: FileDownloadSource
) : FileDownloadRepo {

    override suspend fun downloadPdfFile(): NetworkResponse<ByteArray> {
        return when (
            val response = fileDownloadService.downloadPdfFile()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data)
            is NetworkResponse.Failure -> NetworkResponse.Failure(response.throwable)
            is NetworkResponse.Unauthorized -> NetworkResponse.Unauthorized(response.throwable)
        }
    }
}