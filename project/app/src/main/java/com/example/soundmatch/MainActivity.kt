package com.example.soundmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.soundmatch.screens.CreditsScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.soundmatch.api.ApiService
import com.example.soundmatch.viewmodels.QuizViewModel
import androidx.compose.runtime.getValue
import android.util.Log // <-- Adicione esta importação no topo do arquivo

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
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(navController = navController, startDestination = "menu") {

        composable("menu") {
            MenuScreen(
                onNavigateToQuiz = {
                    quizViewModel.restartQuiz() // Prepara o ViewModel para um novo quiz
                    navController.navigate("quiz")
                },
                onNavigateToCredits = { navController.navigate("credits") }
            )
        }

        composable("credits") {
            CreditsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable("quiz") {
            // Observa a pergunta atual que o ViewModel está nos mandando
            val currentQuestion by quizViewModel.currentQuestion.collectAsState()

            // Observa se o quiz terminou para podermos chamar a API
            val finalAnswers by quizViewModel.quizFinishedWithAnswers.collectAsState()

            // Este efeito será disparado QUANDO o quiz terminar (finalAnswers não for nulo)
            LaunchedEffect(finalAnswers) {
                finalAnswers?.let { answers ->
                    val result = ApiService.getPrediction(answers)

                    // --- ADICIONE ESTAS LINHAS PARA VER A RESPOSTA ---
                    Log.d("API_RESPONSE", "Resultado recebido da API: $result")

                    if (result != null) {
                        val genre = result.genero_previsto.lowercase()
                        navController.navigate("results/$genre") { popUpTo("menu") }
                    } else {
                        // Lida com erro de rede
                        navController.navigate("results/rock") { popUpTo("menu") }
                    }
                }
            }

            // Enquanto o quiz não termina, mostramos a pergunta atual
            currentQuestion?.let { question ->
                QuizScreen(
                    question = question,
                    questionNumber = quizViewModel.getCurrentQuestionNumber(),
                    totalQuestions = 10, // O quiz sempre terá 10 perguntas
                    onAnswerSelected = { answerIndex ->
                        quizViewModel.submitAnswer(question.id, answerIndex)
                    }
                )
            }
        }

        composable(
            route = "results/{resultId}",
            arguments = listOf(navArgument("resultId") { type = NavType.StringType })
        ) { backStackEntry ->
            val resultId = backStackEntry.arguments?.getString("resultId")

            // Usamos containsKey para evitar crash se a API retornar um gênero inesperado
            if (resultId != null && allResults.containsKey(resultId)) {
                val result = allResults.getValue(resultId)
                ResultScreen(
                    result = result,
                    onRedoQuiz = {
                        navController.navigate("menu") { popUpTo("menu") { inclusive = true } }
                    }
                )
            } else {
                // Se o ID for inválido, mostramos um resultado padrão
                val fallbackResult = allResults.getValue("rock")
                ResultScreen(
                    result = fallbackResult,
                    onRedoQuiz = {
                        navController.navigate("menu") { popUpTo("menu") { inclusive = true } }
                    }
                )
            }
        }
    }
}