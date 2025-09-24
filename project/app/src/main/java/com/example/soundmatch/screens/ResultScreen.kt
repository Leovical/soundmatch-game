package com.example.soundmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundmatch.DarkBrownColor
import com.example.soundmatch.OrangeColor
import com.example.soundmatch.R
import com.example.soundmatch.data.ResultData
import com.example.soundmatch.ui.theme.PressStart2PFamily


@Composable
fun ResultScreen(result: ResultData, onRedoQuiz: () -> Unit) {
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
            Spacer(modifier = Modifier.weight(0.3f))

            Text(
                text = "SEU ESTILO MUSICAL É...",
                style = MaterialTheme.typography.titleLarge,
                color = DarkBrownColor,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // title
            Box {
                Text(
                    text = result.genreName,
                    fontFamily = PressStart2PFamily,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 4.dp, y = 4.dp)
                )
                Text(
                    text = result.genreName,
                    fontFamily = PressStart2PFamily,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor,
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            // images
            Image(
                painter = painterResource(id = result.imageResId),
                contentDescription = result.genreName,
                modifier = Modifier.height(225.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(35.dp))

            // description
            Text(
                text = result.description,
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBrownColor,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // keywords
            Text(
                text = result.keywords,
                style = MaterialTheme.typography.titleLarge,
                color = DarkBrownColor,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                lineHeight = 26.sp
            )

            Spacer(modifier = Modifier.weight(0.4f))

            MenuButton(
                text = "VOLTAR AO MENU",
                onClick = onRedoQuiz
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


