package com.paweljablonski.summary.model

data class MQuestionItem(
    val question: String = "",
    val competenceId: String = "",
    val choices: Map<String, Map<String,Any>> = mapOf()
)
