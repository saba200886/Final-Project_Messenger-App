package com.sburnadze.final_project_messenger_app.mainPageFragment

import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.model.User

interface IMainPage {

    fun searchLastChats(name: String)

    fun onLastChatsFound(currLastChats: List<LastMessage>?)

}