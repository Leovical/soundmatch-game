package com.example.soundmatch.api

import com.example.soundmatch.data.QuizRequest
import com.example.soundmatch.data.QuizResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

object ApiService {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private const val BASE_URL = "http://192.168.100.11:5000"

    suspend fun getPrediction(answers: Map<String, String>): QuizResponse? {
        return try {
            val requestBody = QuizRequest(respostas = answers)

            val response: QuizResponse = client.post("$BASE_URL/predict") {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}