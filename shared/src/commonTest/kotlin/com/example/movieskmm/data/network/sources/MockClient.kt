package com.example.movieskmm.data.network.sources

import com.example.movieskmm.BuildKonfig
import com.example.movieskmm.data.util.ApiConstants
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals

val mockSuccessEngine = MockEngine {
    assertEquals(
        ApiConstants.BASE_URL + ApiConstants.ENDPOINT_NOW_PLAYING_MOVIES,
        it.url.toString()
    )
    respond(
        content = """
                    {
  "dates": {
    "maximum": "2024-01-17",
    "minimum": "2023-12-06"
  },
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/f1AQhx6ZfGhPZFTVKgxG91PhEYc.jpg",
      "genre_ids": [
        36,
        10752,
        18
      ],
      "id": 753342,
      "original_language": "en",
      "original_title": "Napoleon",
      "overview": "An epic that details the checkered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.",
      "popularity": 2998.164,
      "poster_path": "/jE5o7y9K6pZtWNNMEw3IdpHuncR.jpg",
      "release_date": "2023-11-22",
      "title": "Napoleon",
      "video": false,
      "vote_average": 6.462,
      "vote_count": 1124
    },
    {
      "adult": false,
      "backdrop_path": "/vdpE5pjJVql5aD6pnzRqlFmgxXf.jpg",
      "genre_ids": [
        18,
        36
      ],
      "id": 906126,
      "original_language": "es",
      "original_title": "La sociedad de la nieve",
      "overview": "On October 13, 1972, Uruguayan Air Force Flight 571, chartered to take a rugby team to Chile, crashes into a glacier in the heart of the Andes.",
      "popularity": 1811.995,
      "poster_path": "/2e853FDVSIso600RqAMunPxiZjq.jpg",
      "release_date": "2023-12-13",
      "title": "Society of the Snow",
      "video": false,
      "vote_average": 8.024,
      "vote_count": 798
    }
  ],
  "total_pages": 1,
  "total_results": 2
}
                """.trimIndent(),
        headers = headersOf(
            HttpHeaders.ContentType,
            ContentType.Application.Json.toString()
        )
    )
}

val mockFailureEngine = MockEngine {
    respond(
        content = "",
        status = HttpStatusCode.NotFound
    )
}

fun getMockHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = BuildKonfig.API_READ_ACCESS_TOKEN,
                        refreshToken = BuildKonfig.API_READ_ACCESS_TOKEN
                    )
                }
            }
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
        }.also {
            Napier.base(DebugAntilog())
        }
    }
}