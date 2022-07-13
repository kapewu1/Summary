package com.paweljablonski.summary.model

import java.sql.RowId

data class MCompetenceResult(
    var name: String = "",
    val competenceId: String = "",
    val userId: String = "",
    var score: Int = 0,
    val docId: String = ""

){

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "name" to this.name,
            "competenceId" to this.competenceId,
            "userId" to this.userId,
            "score" to this.score,
            "docId" to this.docId
        )
    }
}
