package com.sburnadze.final_project_messenger_app.model

import android.media.Image
import android.widget.ImageView
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String? = null, val profession: String? = null,
    var id: String? = null)
