package com.paweljablonski.summary.model

data class MOutcome(
    var id: String? = null,
    var name: String = "",
    var score: Int? = 0,
    var userId: String? = "",
    val competenceId: String = ""
)
