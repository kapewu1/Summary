package com.paweljablonski.summary.model

data class MCompetence(
    var id: String? = null,
    val name: String = "",
    val description: String = "",
    val competenceId: String = ""
){
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "name" to this.name,
            "description" to this.description,
            "competenceId" to this.competenceId
        )
    }
}
