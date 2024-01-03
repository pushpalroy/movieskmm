package com.example.movieskmm.data.network.repository

import com.example.movieskmm.data.network.sources.FileDownloadSource
import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier

class FileDownloadRepoImpl(
    private val fileDownloadService: FileDownloadSource
) : FileDownloadRepo {

    override suspend fun downloadPdfFile(): NetworkResponse<ByteArray> {
        return when (
            val response = fileDownloadService.downloadPdfFile()
        ) {
            is NetworkResponse.Success -> NetworkResponse.Success(response.data)
            is NetworkResponse.Failure -> {
                Napier.e("Error in downloading pdf file: ${response.throwable.message}")
                return NetworkResponse.Failure(response.throwable)
            }

            is NetworkResponse.Unauthorized -> {
                Napier.e("Unauthorized in downloading pdf file: ${response.throwable.message}")
                return NetworkResponse.Unauthorized(response.throwable)
            }
        }
    }
}