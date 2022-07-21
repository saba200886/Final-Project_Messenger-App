package com.sburnadze.final_project_messenger_app

import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.User

interface IChat {
    fun sendMessage(text: String, currUser: String, secondUser: String)

    fun loadMessages(chat: MutableList<ChatMessage>, currUser: String, secondUser: String)
}