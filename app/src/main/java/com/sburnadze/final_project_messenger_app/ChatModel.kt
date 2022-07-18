package com.sburnadze.final_project_messenger_app

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.Message
import com.sburnadze.final_project_messenger_app.model.User

class ChatModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()
    private val chats = Firebase.database.getReference("chats")

    fun sendMessage(text: String, currUser: String, secondUser: String){
        chats.push().setValue(Message(currUser, secondUser, text))
    }
}