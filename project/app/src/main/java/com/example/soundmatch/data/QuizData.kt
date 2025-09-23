package com.example.soundmatch.data

data class Question(
    val id: Int,
    val questionText: String,
    val answers: List<String>
)


val allQuestions = listOf(
    Question(
        id = 1,
        questionText = "Qual destes sons te atrai mais em uma música?",
        answers = listOf(
            "Guitarras elétricas com distorção e uma bateria poderosa.",
            "Sintetizadores, batidas eletrônicas e sons graves (profundos).",
            "A virtuosidade de instrumentos acústicos como piano, violino ou saxofone.",
            "A voz humana clara e em primeiro plano, contando uma história.",
            "Prefiro que os instrumentos criem uma \"paisagem sonora\", sem necessariamente um elemento principal."
        )
    ),
    Question(
        id = 2,
        questionText = "Pense no seu jeito ideal de curtir música. Qual destes cenários te descreve melhor?",
        answers = listOf(
            "Em um grande show ou festival, no meio da multidão, sentindo a energia do ambiente.",
            "Em uma festa ou balada, onde o importante é a batida te fazer mover.",
            "Em um barzinho ou sarau, com música ao vivo, em um clima mais social e descontraído.",
            "Sozinho(a) em casa, com bons fones de ouvido, para uma imersão total e sem distrações.",
            "Como um som de fundo agradável para me concentrar, trabalhar ou relaxar."
        )
    ),
    Question(
        id = 3,
        questionText = "A música, para você, é principalmente uma forma de...",
        answers = listOf(
            "Extravasar energia, agitação ou até raiva.",
            "Dançar, celebrar e socializar com amigos.",
            "Apreciar a técnica, a complexidade e o talento dos músicos.",
            "Conectar-se com sentimentos profundos, nostalgia e poesia.",
            "Relaxar a mente e fugir do estresse do dia a dia."
        )
    ),
    Question(
        id = 4,
        questionText = "Imagine que você está descobrindo uma música nova que te vicia. Qual é a principal característica dela?",
        answers = listOf(
            "A energia contagiante, que me faz querer pular e cantar junto.",
            "A batida (o \"beat\"), forte e marcante, que me faz balançar a cabeça.",
            "A melodia complexa e cheia de nuances, com vários instrumentos trabalhando juntos.",
            "A letra poética e a simplicidade de um violão ou piano que tocam a alma.",
            "A atmosfera que ela cria, sendo calma e relaxante, perfeita para se concentrar."
        )
    ),
    Question(
        id = 5,
        questionText = "Como você prefere os vocais em uma música?",
        answers = listOf(
            "Potentes e rasgados, cheios de atitude e emoção.",
            "Melódicos e limpos, com um refrão que gruda na cabeça.",
            "Ritmo falado, com rimas inteligentes e um bom \"flow\".",
            "Suaves e sussurrados, quase como uma conversa ao pé do ouvido.",
            "Prefiro músicas instrumentais, onde os instrumentos \"falam\" por si."
        )
    ),
    Question(
        id = 6,
        questionText = "O que mais te impressiona em uma composição musical?",
        answers = listOf(
            "Um riff de guitarra ou uma linha de baixo que é instantaneamente reconhecível.",
            "A produção inovadora, com texturas e sons que eu nunca ouvi antes.",
            "Solos instrumentais longos e tecnicamente impressionantes.",
            "A harmonia perfeita entre a letra e a melodia.",
            "A simplicidade genial, que prova que \"menos é mais\"."
        )
    ),
    Question(
        id = 7,
        questionText = "A energia que te move vem principalmente de:",
        answers = listOf(
            "Uma banda com guitarras, baixo e bateria tocando em perfeita sincronia.",
            "Batidas eletrônicas, sintetizadores e um grave que faz tudo tremer.",
            "A velocidade e o peso dos instrumentos, de forma intensa e avassaladora."
        )
    ),
    Question(
        id = 8,
        questionText = "Qual tipo de vocal melhor complementa esse som?",
        answers = listOf(
            "Uma voz melódica e marcante, que canta um refrão poderoso.",
            "Vocais mais agressivos, rasgados ou até mesmo gritados.",
            "Pouco ou nenhum vocal. O foco deve ser na batida e nos instrumentos.",
            "Vocais processados com efeitos, como parte da paisagem sonora."
        )
    ),
    Question(
        id = 9,
        questionText = "O elemento principal que te prende na música é:",
        answers = listOf(
            "O riff de guitarra, aquela melodia que fica na sua cabeça.",
            "A construção de uma batida constante que te hipnotiza e te faz dançar ou pular do chão.",
            "A potência e a complexidade da bateria e do baixo."
        )
    ),
    Question(
        id = 10,
        questionText = "Essa música é a trilha sonora perfeita para:",
        answers = listOf(
            "Cantar junto com os amigos, em uma viagem ou em um bar de rock.",
            "Extravasar toda a sua energia, seja num show ou sozinho em casa.",
            "Uma festa que dura a noite inteira."
        )
    ),
    Question(
        id = 11,
        questionText = "O que é mais importante para você em uma canção?",
        answers = listOf(
            "A mensagem da letra e a interpretação do(a) cantor(a)",
            "A qualidade e a técnica dos instrumentistas, a música falando por si só"
        )
    ),
    Question(
        id = 12,
        questionText = "Sobre o que você mais gosta de ouvir nas letras?",
        answers = listOf(
            "Histórias de amor, romance, festa e cotidiano.",
            "Poesias, críticas sociais ou reflexões mais profundas sobre a vida.",
            "Mensagens de fé, esperança, louvor e gratidão."
        )
    ),
    Question(
        id = 13,
        questionText = "Qual sonoridade te agrada mais como acompanhamento para a voz?",
        answers = listOf(
            "Uma produção moderna, com elementos eletrônicos e uma batida dançante.",
            "O som do violão, da sanfona e do piano, de forma mais acústica e emotiva.",
            "O som do violão, da sanfona e do piano, de forma mais acústica e emotiva.",
            "Uma banda completa, com guitarras, teclado e bateria, em formato de \"hino\"."
        )
    ),
    Question(
        id = 14,
        questionText = "Essa canção te faz sentir principalmente...",
        answers = listOf(
            "Com vontade de dançar e cantar junto um refrão que não sai da cabeça.",
            "Conectado(a) com as suas emoções, seja alegria, sofrimento ou saudade.",
            "Inspirado(a) e com a fé fortalecida.",
            "Orgulhoso(a) da cultura e da riqueza musical brasileira."
        )
    ),
    Question(
        id = 15,
        questionText = "Qual tipo de conjunto instrumental te fascina?",
        answers = listOf(
            "Uma grande orquestra sinfônica, com dezenas de músicos em harmonia.",
            "Uma banda menor (um trio ou quarteto), onde cada músico tem seu momento de brilhar e improvisar."
        )
    ),
    Question(
        id = 16,
        questionText = "Como você descreveria a estrutura musical que você prefere?",
        answers = listOf(
            "Grandiosa, estruturada em movimentos, com temas que se desenvolvem ao longo de minutos.",
            "Livre, com um tema principal que serve de base para a criatividade e o improviso espontâneo."
        )
    ),
    Question(
        id = 17,
        questionText = "Esse tipo de música é ideal para:",
        answers = listOf(
            "Se concentrar, estudar ou apreciar em silêncio, como em uma sala de concerto.",
            "Relaxar em um ambiente sofisticado, como um bar ou um clube, admirando o talento dos músicos."
        )
    )
)