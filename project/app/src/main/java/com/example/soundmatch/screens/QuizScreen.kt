package com.example.soundmatch.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundmatch.DarkBrownColor
import com.example.soundmatch.OrangeColor
import com.example.soundmatch.R
import com.example.soundmatch.ui.theme.PressStart2PFamily
import com.example.soundmatch.data.Question
import androidx.compose.ui.tooling.preview.Preview
import com.example.soundmatch.data.allQuestions
import com.example.soundmatch.ui.theme.SoundMatchTheme

// main composable
@Composable
fun QuizScreen(
    question: Question,
    questionNumber: Int,
    totalQuestions: Int,
    onAnswerSelected: (answerIndex: Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val scrollState = rememberScrollState()

        Image(
            painter = painterResource(id = R.drawable.background_texture),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // question title
            Box {
                Text(
                    text = question.questionText,
                    fontFamily = PressStart2PFamily,
                    fontSize = 26.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 3.dp, y = 3.dp)
                )
                Text(
                    text = question.questionText,
                    fontFamily = PressStart2PFamily,
                    fontSize = 26.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // question indicator
            Text(
                text = "--- PERGUNTA $questionNumber DE $totalQuestions ---",
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBrownColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            // gennerate buttons and the answers
            question.answers.forEachIndexed { index, answerText ->
                val option = 'A' + index // 'A', 'B', 'C', ...
                AnswerButton(option = option, text = answerText, onClick = { onAnswerSelected(index) })
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

// composable to the button and customizable answer
@Composable
fun AnswerButton(option: Char, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = OrangeColor),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 110.dp),
        border = BorderStroke(4.dp, DarkBrownColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$option)",
                style = MaterialTheme.typography.labelLarge,
                color = DarkBrownColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBrownColor,
                fontSize = 16.sp,
                lineHeight = 20.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    SoundMatchTheme {
        val firstQuestion = allQuestions.first()
        QuizScreen(
            question = firstQuestion,
            questionNumber = 1,
            totalQuestions = allQuestions.size,
            onAnswerSelected = {}
        )
    }
}