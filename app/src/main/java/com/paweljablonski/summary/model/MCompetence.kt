package com.paweljablonski.summary.model

data class MCompetence(
    var id: String? = null,
    var name: String,
    var description: String,
    var score: Int? = 0
    //komentarze
    //ilość osób które oceniło
)
