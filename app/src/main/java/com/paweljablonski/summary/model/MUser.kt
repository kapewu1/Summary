package com.paweljablonski.summary.model

data class MUser(
    val id: String?,
    val userId: String,
    val displayName:String,
    val avatarUrl: String,
    val bio: String,
    var department: MDepartment? = null
){
    fun toMap() : MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "bio" to this.bio,
//            "group_label" to department!!.groupLabel,
//            "group_id" to department!!.groupId,
            "avatar_url" to avatarUrl

        )
    }

//    fun toUser(map: MutableMap<String, Any>): MUser{
//        return MUser(
//            map["user_id"].toString(),
//            map["display_name"].toString(),
//            map["avatarUrl"].toString(),
//            map["bio"].toString(),
//            map["department"].toString(),
//            map["user_id"].toString(),
//
//
//        )
//    }
}
