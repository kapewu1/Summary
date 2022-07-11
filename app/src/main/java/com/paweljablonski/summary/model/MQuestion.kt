package com.paweljablonski.summary.model

data class MQuestion(
    val question: String = "",
    val competenceId: String = "",
    val choices: List<Map<String,Any>> = listOf()
)
