package com.sburnadze.final_project_messenger_app

import android.widget.ImageView

interface IAuthorization {

    fun login(user: String, pass: String)

    fun register(user: String, pass: String, whatIDo: String, image: ImageView)
}
