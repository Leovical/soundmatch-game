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

        // Pega as 6 perguntas base de forma aleatória
        questionSequence = allQuestions.filter { it.isBaseQuestion }.shuffled().take(6).toMutableList()
        _currentQuestion.value = questionSequence.firstOrNull()
    }

    fun submitAnswer(questionId: Int, answerIndex: Int) {
        viewModelScope.launch {
            val answerLetter = ('A' + answerIndex).toString()
            val questionKey = getQuestionKey(questionId, currentBranch)
            userAnswers[questionKey] = answerLetter

            questionCounter++

            // Após as 6 perguntas base, decide o ramo e adiciona as perguntas adaptativas
            if (questionCounter == 6) {
                determineBranch()
                val adaptiveQuestions = selectAdaptiveQuestions()
                questionSequence.addAll(adaptiveQuestions)
            }

            // Avança para a próxima pergunta ou finaliza
            if (questionCounter < 10) { // O quiz agora tem 10 perguntas no total
                _currentQuestion.value = questionSequence[questionCounter]
            } else {
                _currentQuestion.value = null
                _quizFinishedWithAnswers.value = userAnswers
            }
        }
    }

    private fun determineBranch() {
        // Lógica de pontos para decidir o ramo (Ramo A: Respostas A/B, Ramo B: Respostas C/D/E)
        val pointsA = userAnswers.values.count { it == "A" || it == "B" }
        currentBranch = if (pointsA >= 3) "A" else "B" // Se tiver 3 ou mais pontos A/B, vai para o Ramo A
    }

    private fun selectAdaptiveQuestions(): List<Question> {
        return if (currentBranch == "A") {
            // Se for Ramo A, pega 4 perguntas aleatórias do pool do Ramo A (IDs 7-10)
            allQuestions.filter { it.id in 7..10 }.shuffled().take(4)
        } else {
            // Se for Ramo B, pega 4 perguntas aleatórias do pool do Ramo B (IDs 11-17)
            allQuestions.filter { it.id in 11..17 }.shuffled().take(4)
        }
    }

    // !! ESTA É A FUNÇÃO DE TRADUÇÃO QUE FALTAVA !!
    private fun getQuestionKey(questionId: Int, branch: String?): String {
        return when (questionId) {
            1 -> "Q1"
            2 -> "Q2"
            3 -> "Q3"
            4 -> "Q4"
            5 -> "Q5"
            6 -> "Q6"
            // Para as perguntas adaptativas, agora usamos o ramo para diferenciar
            7 -> "Q7A"
            8 -> "Q8A"
            9 -> "Q9A"
            10 -> "Q10A"
            11 -> "Q7B"
            12 -> "Q8B1"
            13 -> "Q9B1"
            14 -> "Q10B1"
            15 -> "Q8B2"
            16 -> "Q9B2"
            17 -> "Q10B2"
            else -> "UNKNOWN_KEY_$questionId" // Segurança para evitar erros
        }
    }

    fun getCurrentQuestionNumber(): Int {
        return questionCounter + 1
    }

    fun restartQuiz() {
        startQuiz()
    }
}