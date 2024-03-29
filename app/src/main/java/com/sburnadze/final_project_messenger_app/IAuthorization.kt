package com.sburnadze.final_project_messenger_app

import android.widget.ImageView
import com.sburnadze.final_project_messenger_app.model.User

interface IAuthorization {

    fun login(user: String, pass: String)

    fun register(user: String, pass: String, whatIDo: String)

    fun signOut()

    fun update(name: String, whatIDo: String)

}
