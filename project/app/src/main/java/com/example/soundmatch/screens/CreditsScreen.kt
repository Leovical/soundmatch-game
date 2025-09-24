package com.example.soundmatch.screens

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
import com.example.soundmatch.ui.theme.PressStart2PFamily
import com.example.soundmatch.ui.theme.SoundMatchTheme

@Composable
fun CreditsScreen(onNavigateBack: () -> Unit) {
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
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            // Título "CRÉDITOS"
            Box {
                Text(
                    text = "CRÉDITOS",
                    fontFamily = PressStart2PFamily,
                    fontSize = 40.sp, // Um pouco menor que o título principal
                    textAlign = TextAlign.Center,
                    color = DarkBrownColor,
                    modifier = Modifier.offset(x = 4.dp, y = 4.dp)
                )
                Text(
                    text = "CRÉDITOS",
                    fontFamily = PressStart2PFamily,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = OrangeColor,
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Seção de informações dos créditos
            CreditEntry("Desenvolvimento do app:", "Leonardo Victor")
            Spacer(modifier = Modifier.height(40.dp))
            CreditEntry("Documentação e Machine Learning:", "João Paulo Brito")

            // Este Spacer empurra o conteúdo abaixo para o fundo
            Spacer(modifier = Modifier.weight(1f))

            // Botão para voltar ao menu
            MenuButton(text = "VOLTAR", onClick = onNavigateBack)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun CreditEntry(role: String, name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = role,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp,
            color = DarkBrownColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontFamily = PressStart2PFamily,
            fontSize = 22.sp,
            color = OrangeColor,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreditsScreenPreview() {
    SoundMatchTheme {
        CreditsScreen(onNavigateBack = {})
    }
}