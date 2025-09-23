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
import com.example.soundmatch.screens.MenuScreen
import com.example.soundmatch.screens.QuizScreen
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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(onNavigateToQuiz = {
                navController.navigate("quiz/1")
            })
        }


        composable("quiz/{questionId}") { backStackEntry ->
            val questionId = backStackEntry.arguments?.getString("questionId")?.toIntOrNull() ?: 1

            val question = allQuestions.find { it.id == questionId }

            if (question != null) {
                val nextQuestionId = questionId + 1

                QuizScreen(
                    question = question,
                    questionNumber = question.id,
                    totalQuestions = allQuestions.size,
                    onAnswerSelected = { answerIndex ->

                        if (nextQuestionId > allQuestions.size) {
                        } else {
                            navController.navigate("quiz/$nextQuestionId")
                        }
                    }
                )
            }
        }

        // composable("results") { ResultsScreen() }
    }
}
