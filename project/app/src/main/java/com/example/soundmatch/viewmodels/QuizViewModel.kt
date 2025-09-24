package com.example.soundmatch.viewmodels

import androidx.lifecycle.ViewModel
import com.example.soundmatch.data.Question
import com.example.soundmatch.data.allQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {

    private var questionSequence = listOf<Question>()
    private val userAnswers = mutableMapOf<String, String>()

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion = _currentQuestion.asStateFlow()

    private val _quizFinishedWithAnswers = MutableStateFlow<Map<String, String>?>(null)
    val quizFinishedWithAnswers = _quizFinishedWithAnswers.asStateFlow()

    init {
        startQuiz()
    }

    private fun startQuiz() {
        userAnswers.clear()
        questionSequence = allQuestions.filter { it.isBaseQuestion }.shuffled().take(6)
        _currentQuestion.value = questionSequence.firstOrNull()
        _quizFinishedWithAnswers.value = null
    }

    fun submitAnswer(questionId: Int, answerIndex: Int) {
        val answerLetter = ('A' + answerIndex).toString()
        userAnswers["Q$questionId"] = answerLetter

        if (userAnswers.size == 6) {
            val adaptiveQuestions = selectAdaptiveQuestions(userAnswers)
            questionSequence = questionSequence + adaptiveQuestions
        }

        val currentIndex = questionSequence.indexOfFirst { it.id == questionId }
        val nextIndex = currentIndex + 1

        if (nextIndex < questionSequence.size) {
            _currentQuestion.value = questionSequence[nextIndex]
        } else {
            _quizFinishedWithAnswers.value = userAnswers
        }
    }

    private fun selectAdaptiveQuestions(baseAnswers: Map<String, String>): List<Question> {
        val adaptivePool = allQuestions.filter { !it.isBaseQuestion }
        return adaptivePool.shuffled().take(4)
    }

    fun getCurrentQuestionNumber(): Int {
        return userAnswers.size + 1
    }

    fun restartQuiz() {
        startQuiz()
    }
}