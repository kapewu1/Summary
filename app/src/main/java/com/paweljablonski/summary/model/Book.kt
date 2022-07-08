package com.paweljablonski.summary.model

import com.google.firebase.firestore.Exclude

class Book (
    @Exclude var id: String? = null,
    var title: String? = null,
    var subject: String? = null
        ){
}