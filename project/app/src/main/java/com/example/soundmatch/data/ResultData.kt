package com.example.soundmatch.data

import androidx.annotation.DrawableRes
import com.example.soundmatch.R

data class ResultData(
    val id: String,
    val genreName: String,
    @DrawableRes val imageResId: Int,
    val description: String,
    val keywords: String
)

val allResults = mapOf(
    "rock" to ResultData(
        id = "rock",
        genreName = "ROCK",
        imageResId = R.drawable.rock,
        description = "Você gosta de energia intensa e se identifica com sons marcantes e cheios de atitude.",
        keywords = "GUITARRAS PODEROSAS, SONS INTENSOS E LETRAS IMPRESSIONANTES."
    ),
    "eletronica" to ResultData(
        id = "eletronica",
        genreName = "ELETRÔNICA",
        imageResId = R.drawable.eletronica,
        description = "Você gosta de batidas vibrantes que fazem o corpo se mover e a mente viajar.",
        keywords = "SINTETIZADORES ENVOLVENTES, RITMOS DANÇANTES E ENERGIA SEM FIM."
    ),
    "metal" to ResultData(
        id = "metal",
        genreName = "METAL",
        imageResId = R.drawable.heavy_metal,
        description = "Você busca intensidade e sons pesados que traduzem força e emoção.",
        keywords = "RIFFS PESADOS, VOCAL IMPACTANTE E ADRENALINA PURA."
    ),
    "mpb" to ResultData(
        id = "mpb",
        genreName = "MPB",
        imageResId = R.drawable.mpb,
        description = "Você aprecia músicas que unem poesia, ritmo e identidade cultural.",
        keywords = "VIOLÃO MARCANTE, VOZ EXPRESSIVA E TRADIÇÃO BRASILEIRA."
    ),
    "classica" to ResultData(
        id = "classica",
        genreName = "CLASSICA",
        imageResId = R.drawable.classica,
        description = "Você valoriza a sofisticação e se emociona com a beleza de grandes composições.",
        keywords = "ORQUESTRAS HARMÔNICAS, INSTRUMENTOS ELEGANTES E PROFUNDIDADE EMOCIONAL."
    ),
    "jazz" to ResultData(
        id = "jazz",
        genreName = "JAZZ",
        imageResId = R.drawable.jazz,
        description = "Você curte improviso, liberdade musical e sons que fluem naturalmente.",
        keywords = "SAXOFONE ENVOLVENTE, PIANO EXPRESSIVO E RITMOS INOVADORES."
    ),
    "sertanejo" to ResultData(
        id = "sertanejo",
        genreName = "SERTANEJO",
        imageResId = R.drawable.sertanejo,
        description = "Você se conecta com emoções fortes, histórias de amor e momentos de festa.",
        keywords = "VIOLÃO, SANFONA E LETRAS QUE FALAM DE ROMANCE E VIDA SIMPLES."
    ),
    "pop" to ResultData(
        id = "pop",
        genreName = "POP",
        imageResId = R.drawable.pop,
        description = "Você se conecta com músicas cativantes e letras que falam sobre o cotidiano.",
        keywords = "REFRÕES GRUDADOS NA CABEÇA, RITMOS LEVES E UNIVERSAIS."
    ),
    "gospel" to ResultData(
        id = "gospel",
        genreName = "GOSPEL",
        imageResId = R.drawable.gospel,
        description = "Você encontra inspiração e força espiritual através da música.",
        keywords = "LETRAS DE FÉ, HARMONIAS ENVOLVENTES E MENSAGEM DE ESPERANÇA."
    )
)