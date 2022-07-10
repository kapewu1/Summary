package com.paweljablonski.summary.model

import com.google.firebase.firestore.Exclude

data class MUser(
    @Exclude val id: String? = null,
    val userId: String = "",
    val displayName: String = "New User",
    val avatarUrl: String = "",
    var bio: String = "",
    var isEvaluated: Boolean = false
){


    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "avatar_url" to this.avatarUrl,
            "bio" to this.bio,
            "is_evaluated" to this.isEvaluated
        )
    }
}

