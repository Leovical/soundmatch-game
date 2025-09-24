package com.example.soundmatch.data

import kotlinx.serialization.Serializable

@Serializable
data class QuizRequest(
    val respostas: Map<String, String>
)

@Serializable
data class QuizResponse(
    val genero_previsto: String
)