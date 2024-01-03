package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.util.NetworkResponse
import io.github.aakira.napier.Napier

class FileDownloadUseCase(
    private val fileDownloadRepo: FileDownloadRepo
) : UseCase<NetworkResponse<ByteArray>, Unit> {
    override suspend fun perform(): NetworkResponse<ByteArray> {
        return try {
            fileDownloadRepo.downloadPdfFile()
        } catch (e: Exception) {
            Napier.e("Exception in downloading file: ${e.message}")
            NetworkResponse.Failure(e)
        }
    }
}