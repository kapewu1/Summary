package com.paweljablonski.summary.model

data class MEmployee(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    , //todo : to change, user need to have context
    var avatarUrl: String? = null,
    var bio: String? = null,


)
