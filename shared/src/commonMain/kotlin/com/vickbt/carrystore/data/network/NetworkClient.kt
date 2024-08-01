package com.vickbt.carrystore.data.network

import com.vickbt.carrystore.domain.utils.Constants.BASE_URL
import com.vickbt.carrystore.domain.utils.Constants.URL_PATH
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {
    val httpClient = HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path(URL_PATH)
            }
        }

        install(Logging) {
            level = LogLevel.HEADERS
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.i(tag = "Http Client", message = message)
                }
            }
        }.also {
            Napier.base(DebugAntilog())
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}