package com.example.movieskmm.domain.usecase

import com.example.movieskmm.domain.repo.FileDownloadRepo
import com.example.movieskmm.domain.util.NetworkResponse

class FileDownloadUseCase(
    private val fileDownloadRepo: FileDownloadRepo
) : UseCase<NetworkResponse<ByteArray>, Unit> {
    override suspend fun perform(): NetworkResponse<ByteArray> {
        return fileDownloadRepo.downloadPdfFile()
    }
}