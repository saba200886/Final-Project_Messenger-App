package com.sburnadze.final_project_messenger_app.mainPageFragment

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.model.User


class MainPageFragmentModel(private val mainPageViewModel: MainPageViewModel)  {

    private val chats = Firebase.database.getReference("chats")

    fun searchLastChats(currUser: String) {
        val currLastChats = mutableListOf<ChatMessage>()

        var lastMessage: ChatMessage? = null
        chats.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val curChat = it.getValue(ChatMessage::class.java) as ChatMessage
                    if (curChat.sender == currUser || curChat.receiver == currUser){
                        lastMessage = curChat
                    }
                }

                currLastChats.add(lastMessage!!)

                mainPageViewModel.onLastChatsFound(currLastChats)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}