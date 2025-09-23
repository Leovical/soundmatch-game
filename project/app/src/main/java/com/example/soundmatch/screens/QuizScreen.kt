package com.example.soundmatch.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

// main composable
@Composable
fun QuizScreen() {
    // data example, after ML
    val questionText = "Qual destes sons te atrai mais em uma música?"
    val answers = listOf(
        "Guitarras elétricas com distorção e uma bateria poderosa.",
        "Sintetizadores, batidas eletrônicas e sons graves (profundos).",
        "A virtuosidade de instrumentos acústicos como piano, violino ou saxofone.",
        "A voz humana clara e em primeiro plano, contando uma história."
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_texture),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // question title
            Box {
                Text(
                    text = questionText,
                    fontFamily = PressStart2PFamily,
                    fontSize = 30.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 3.dp, y = 3.dp)
                )
                Text(
                    text = questionText,
                    fontFamily = PressStart2PFamily,
                    fontSize = 30.sp,
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // question indicator
            Text(
                text = "--- PERGUNTA 1 DE 10 ---",
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBrownColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            // gennerate buttons and the answers
            answers.forEachIndexed { index, answerText ->
                val option = 'A' + index // 'A', 'B', 'C', ...
                AnswerButton(option = option, text = answerText, onClick = { /* Lógica da resposta */ })
                Spacer(modifier = Modifier.height(20.dp))
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
            .defaultMinSize(minHeight = 80.dp),
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
                fontSize = 18.sp,
                lineHeight = 22.sp,
            )
        }
    }
}