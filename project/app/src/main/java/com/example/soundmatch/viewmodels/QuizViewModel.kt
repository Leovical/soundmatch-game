package com.example.soundmatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundmatch.data.Question
import com.example.soundmatch.data.allQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    private var questionSequence = mutableListOf<Question>()
    private val userAnswers = mutableMapOf<String, String>()

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion = _currentQuestion.asStateFlow()

    private val _quizFinishedWithAnswers = MutableStateFlow<Map<String, String>?>(null)
    val quizFinishedWithAnswers = _quizFinishedWithAnswers.asStateFlow()

    private var questionCounter = 0
    private var currentBranch: String? = null

    init {
        startQuiz()
    }

    private fun startQuiz() {
        questionCounter = 0
        userAnswers.clear()
        currentBranch = null
        _quizFinishedWithAnswers.value = null

        // Pega as 6 perguntas base em ordem fixa pelo ID
        questionSequence = allQuestions.filter { it.isBaseQuestion }.sortedBy { it.id }.toMutableList()
        _currentQuestion.value = questionSequence.firstOrNull()
    }

    fun submitAnswer(questionId: Int, answerIndex: Int) {
        viewModelScope.launch {
            val answerLetter = ('A' + answerIndex).toString()
            val questionKey = getQuestionKey(questionId)
            userAnswers[questionKey] = answerLetter

            questionCounter++

            // Lógica para determinar o que fazer a seguir
            when (questionCounter) {
                // Após a 6ª pergunta, determina o ramo principal
                6 -> {
                    determineBranch()
                    addNextBranchQuestion()
                }
                // No Ramo B, após a 7ª pergunta (a 1ª do ramo), determina a sub-ramificação
                7 -> {
                    if (currentBranch == "B") {
                        addSubBranchBQuestions(answerIndex)
                    }
                    moveToNextQuestionInSequence()
                }
                // Para as demais perguntas, apenas avança na sequência
                else -> {
                    moveToNextQuestionInSequence()
                }
            }
        }
    }

    private fun determineBranch() {
        val pointsA = userAnswers.values.count { it == "A" || it == "B" }
        currentBranch = if (pointsA >= 3) "A" else "B"
    }

    private fun addNextBranchQuestion() {
        if (currentBranch == "A") {
            // Se for Ramo A, adiciona as 4 perguntas do Ramo A em ordem
            val adaptiveQuestions = allQuestions.filter { it.id in 7..10 }.sortedBy { it.id }
            questionSequence.addAll(adaptiveQuestions)
        } else {
            // Se for Ramo B, adiciona APENAS a primeira pergunta do Ramo B (ID 11)
            val nextQuestion = allQuestions.first { it.id == 11 }
            questionSequence.add(nextQuestion)
        }
        moveToNextQuestionInSequence()
    }

    private fun addSubBranchBQuestions(answerIndexOfQ7B: Int) {
        val subBranchQuestions = if (answerIndexOfQ7B == 0) { // Resposta A para a 7.B
            // Adiciona as 3 perguntas do "Foco na Canção" (IDs 12, 13, 14) em ordem
            allQuestions.filter { it.id in 12..14 }.sortedBy { it.id }
        } else { // Resposta B para a 7.B
            // Adiciona as 3 perguntas do "Foco Instrumental" (IDs 15, 16, 17) em ordem
            allQuestions.filter { it.id in 15..17 }.sortedBy { it.id }
        }
        questionSequence.addAll(subBranchQuestions)
    }

    private fun moveToNextQuestionInSequence() {
        if (questionCounter < 10) {
            _currentQuestion.value = questionSequence[questionCounter]
        } else {
            _currentQuestion.value = null
            _quizFinishedWithAnswers.value = userAnswers
        }
    }

    private fun getQuestionKey(questionId: Int): String {
        return when (questionId) {
            1 -> "Q1"; 2 -> "Q2"; 3 -> "Q3"; 4 -> "Q4"; 5 -> "Q5"; 6 -> "Q6"
            7 -> "Q7A"; 8 -> "Q8A"; 9 -> "Q9A"; 10 -> "Q10A"
            11 -> "Q7B"
            12 -> "Q8B1"; 13 -> "Q9B1"; 14 -> "Q10B1"
            15 -> "Q8B2"; 16 -> "Q9B2"; 17 -> "Q10B2"
            else -> "UNKNOWN_KEY_$questionId"
        }
    }

    fun getCurrentQuestionNumber(): Int {
        return questionCounter + 1
    }

    fun restartQuiz() {
        startQuiz()
    }
}