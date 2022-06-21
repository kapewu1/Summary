package com.paweljablonski.summary.model

data class MSurvey(
    var id: String? = null,
    var employee: MUser? = null,
    var evaluator: MUser? = null, //set by currentLogged user
    var comment: String? = null,
    var questions: List<MQuestion>? = null
)
