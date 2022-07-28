package com.sburnadze.final_project_messenger_app

import android.os.Message
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.User

interface IChat {
    fun sendMessage(text: String, currUser: String, secondUser: String)

    fun loadMessages(chat: MutableList<ChatMessage>, currUser: String, secondUser: String)

    fun onMessagesFound(currUsers: List<ChatMessage>?)
}