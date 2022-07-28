package com.sburnadze.final_project_messenger_app

import android.os.Message
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.User

interface IChatMainView {

    fun showFoundMessages(currMessages: List<ChatMessage>?)
}