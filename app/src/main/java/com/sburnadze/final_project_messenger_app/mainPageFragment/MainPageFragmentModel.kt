package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.util.Log
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
    private val users = Firebase.database.getReference("users")

    fun searchLastChats(currUser: String) {
        val currLastChats = mutableListOf<LastMessage>()

        var lastMessage: ChatMessage? = null
        var username = ""

        chats.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val curChat = it.getValue(ChatMessage::class.java) as ChatMessage
                    if (curChat.sender == currUser || curChat.receiver == currUser){
                        lastMessage = curChat
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        users.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    if (it.key == lastMessage?.receiver){
                        username = (it.getValue(User::class.java) as User).name.toString()
                    }
                }

                val res = LastMessage(username, lastMessage!!)
                currLastChats.add(res)

                mainPageViewModel.onLastChatsFound(currLastChats)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}