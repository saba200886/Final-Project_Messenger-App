package com.sburnadze.final_project_messenger_app.model

import android.media.Image
import android.widget.ImageView
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String? = null, val password: String? = null, val profession: String? = null,
    val avatar: ImageView, val id: String?)
