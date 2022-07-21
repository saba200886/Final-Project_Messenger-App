package com.sburnadze.final_project_messenger_app

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.ChatMessage

class ChatModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var chats = Firebase.database.getReference("chats")

    fun sendMessage(text: String, currUser: String, secondUser: String){
        chats.push().setValue(ChatMessage(currUser, secondUser, text))
    }

    fun loadMessages(chat: MutableList<ChatMessage>, currUser: String, secondUser: String){
        chats.addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    chat.clear()
                    snapshot.children.forEach{
                        val message = it.getValue(ChatMessage::class.java)
                        if(message?.let { it1 -> check(it1, currUser, secondUser) } == true){
                            chat.add(message)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    private fun check(message: ChatMessage, currUser: String, secondUser: String): Boolean{
        if(message.sender.equals(currUser) && message.receiver.equals(secondUser) ||
                message.sender.equals(secondUser) && message.receiver.equals(currUser)){
            return true
        }
        return false
    }
}