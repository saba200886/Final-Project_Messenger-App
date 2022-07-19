package com.sburnadze.final_project_messenger_app.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.sburnadze.final_project_messenger_app.ChatModel
import com.sburnadze.final_project_messenger_app.IChat
import com.sburnadze.final_project_messenger_app.model.ChatMessage

class ChatViewModel(private val act: Activity): ViewModel(), IChat {
    private val chatModel = ChatModel(act)

    override fun sendMessage(text: String, currUser: String, secondUser: String) {
        chatModel.sendMessage(text, currUser, secondUser)
    }

    override fun loadMessages(chat: MutableList<ChatMessage>, currUser: String, secondUser: String) {
        chatModel.loadMessages(chat,currUser, secondUser)
    }
}