package com.example.soundmatch.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundmatch.DarkBrownColor
import com.example.soundmatch.OrangeColor
import com.example.soundmatch.R
import com.example.soundmatch.ui.theme.PressStart2PFamily
import com.example.soundmatch.ui.theme.SoundMatchTheme

@Composable
fun MenuScreen(onNavigateToQuiz: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_texture),
            contentDescription = "Background texture",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.SpaceAround
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            // título "SOUND"
            Box {
                Text(
                    text = "SOUND",
                    fontFamily = PressStart2PFamily,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 4.dp, y = 4.dp)
                )
                Text(
                    text = "SOUND",
                    fontFamily = PressStart2PFamily,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // title MATCH
            Box {
                Text(
                    text = "MATCH",
                    fontFamily = PressStart2PFamily,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 4.dp, y = 4.dp)
                )
                Text(
                    text = "MATCH",
                    fontFamily = PressStart2PFamily,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor,
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "♪ Descubra seu \nestilo musical ♪",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                color = DarkBrownColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))

            // BOTÕES
            MenuButton(text = "INICIAR QUIZ", onClick = onNavigateToQuiz)
            Spacer(modifier = Modifier.height(24.dp))
            MenuButton(text = "CRÉDITOS", onClick = { /* ACTION */ })
            Spacer(modifier = Modifier.height(24.dp))
            MenuButton(text = "CONFIGURAÇÕES", onClick = { /* ACTION */ })

            Spacer(modifier = Modifier.weight(1f))

            val imageSize = 260.dp // images size

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize + 32.dp)
            ) {
                // guitar position
                val guitarraOffsetX = (-50).dp
                val guitarraOffsetY = 0.dp

                // piano position
                val pianoOffsetX = 50.dp
                val pianoOffsetY = 0.dp

                // guitar
                Image(
                    painter = painterResource(id = R.drawable.electricguitar_pixelart),
                    contentDescription = "Guitarra elétrica pixel art",
                    modifier = Modifier
                        .offset(x = guitarraOffsetX, y = guitarraOffsetY)
                        .align(Alignment.BottomStart)
                        .padding(start = 1.dp, bottom = 16.dp)
                        .width(imageSize),
                    contentScale = ContentScale.Fit
                )
                // piano
                Image(
                    painter = painterResource(id = R.drawable.piano_pixelart),
                    contentDescription = "Piano pixel art",
                    modifier = Modifier
                        .offset(x = pianoOffsetX, y = pianoOffsetY)
                        .align(Alignment.BottomEnd)
                        .padding(end = 1.dp, bottom = 16.dp)
                        .width(imageSize),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OrangeColor
        ),
        modifier = Modifier
            .width(300.dp)
            .height(70.dp),
        border = BorderStroke(4.dp, DarkBrownColor)
    ) {
        Text(
            text = text,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge,
            color = DarkBrownColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SoundMatchTheme {
        // Chamamos a MenuScreen, passando uma função vazia para o clique
        MenuScreen(onNavigateToQuiz = {})
    }
}