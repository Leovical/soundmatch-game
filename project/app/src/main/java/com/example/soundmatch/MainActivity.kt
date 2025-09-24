package com.example.soundmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soundmatch.data.allQuestions
import com.example.soundmatch.data.allResults
import com.example.soundmatch.screens.MenuScreen
import com.example.soundmatch.screens.QuizScreen
import com.example.soundmatch.screens.ResultScreen
import com.example.soundmatch.ui.theme.SoundMatchTheme

val OrangeColor = Color(0xFFB45329)
val DarkBrownColor = Color(0xFF2C170B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundMatchTheme {
                AppNavigation()
            }
        }
    }
}

// Dentro de MainActivity.kt

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "results/eletronica") {
        // Rota do Menu (continua igual)
        composable("menu") {
            MenuScreen(onNavigateToQuiz = {
                navController.navigate("quiz/1")
            })
        }

        // Rota do Quiz (com a correção)
        composable("quiz/{questionId}") { backStackEntry ->
            // Pegamos o ID da pergunta da rota
            val questionId = backStackEntry.arguments?.getString("questionId")?.toIntOrNull() ?: 1

            // Encontramos a pergunta correspondente na nossa lista
            val question = allQuestions.find { it.id == questionId }

            // Verificamos se a pergunta foi encontrada antes de usá-la
            if (question != null) {
                val nextQuestionId = questionId + 1

                // AQUI ESTÁ A CORREÇÃO:
                // Passamos todos os parâmetros que a QuizScreen espera receber.
                QuizScreen(
                    question = question,
                    questionNumber = question.id,
                    totalQuestions = allQuestions.size,
                    onAnswerSelected = { answerIndex ->

                        if (nextQuestionId > allQuestions.size) {
                            // end quiz, result screen
                            navController.navigate("results/metal") {
                                popUpTo("menu") // clean history
                            }
                        } else {
                            navController.navigate("quiz/$nextQuestionId")
                        }
                    }
                )
            }
        }

        // new route
        composable("results/{resultId}") { backStackEntry ->

            val resultId = backStackEntry.arguments?.getString("resultId") ?: "metal"

            // finding data
            val result = allResults.getValue(resultId)

            ResultScreen(
                result = result,
                onRedoQuiz = {
                    // return to first question
                    navController.navigate("menu") {
                        popUpTo("menu") // clean history
                    }
                }
            )
        }
    }
}
