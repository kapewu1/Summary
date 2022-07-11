package com.paweljablonski.summary.model

data class MQuestionItem(
    val question: String = "",
    val competenceId: String = "",
    val choices: List<Map<String,Any>> = listOf()
)
