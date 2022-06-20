package com.paweljablonski.summary.model

data class MUser(
    val id: String?,
    val userId: String,
    val displayName:String,
    val avatarUrl: String,
    val bio: String,
    val groupId: String,
    val groupLabel: String
){
    fun toMap() : MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "bio" to this.bio,
            "group_label" to groupLabel,
            "group_id" to groupId,
            "avatar_url" to avatarUrl

        )
    }
}
