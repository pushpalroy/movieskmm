package com.example.movieskmm.data.network.sources

import com.example.movieskmm.domain.util.NetworkResponse
import com.example.movieskmm.domain.util.getSafeNetworkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FileDownloadSourceImpl(
    private val httpClient: HttpClient
) : FileDownloadSource {
    override suspend fun downloadPdfFile(): NetworkResponse<ByteArray> =
        getSafeNetworkResponse {
            httpClient.get("file-example_PDF_1MB.pdf").body()
        }
}
