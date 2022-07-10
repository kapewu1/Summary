package com.paweljablonski.summary.model


data class MUser(
    val id: String? = null,
    val userId: String = "",
    var displayName: String= "",
    var avatarUrl: String= "" ,
    var bio: String= "",
    var isEvaluated: Boolean = false){

//    fun toMap(): MutableMap<String, Any> {
//        return mutableMapOf(
//            "user_id" to this.userId,
//            "display_name" to this.displayName,
//            "avatar_url" to this.avatarUrl,
//            "avatar_url" to this.avatarUrl,
//            "avatar_url" to this.avatarUrl
//        )
//    }
}


//
//data class MUser(
//    val id: String?,
//    val userId: String? = "",
//    val displayName: String? = "",
//    val avatarUrl: String? = "",
//    var bio: String? = "",
//    var isEvaluated: Boolean = false
//){


//    fun toMap(): MutableMap<String, Any> {
//        return mutableMapOf(
//            "display_name" to this.displayName,
//            "avatar_url" to this.avatarUrl,
//            "bio" to this.bio,
//            "is_evaluated" to this.isEvaluated
//        )
//    }
//}

