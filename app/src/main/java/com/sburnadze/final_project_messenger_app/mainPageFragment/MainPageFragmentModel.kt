package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.os.Build
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
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

    fun searchLastChats(currUser: String, name: String) {
        val chatsMap = mutableMapOf<String, ChatMessage>()
        val chatsMapFinal = mutableMapOf<String, ChatMessage>()
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

                    var userStr = lastMessage?.sender
                    if(lastMessage?.receiver != currUser)
                        userStr = lastMessage?.receiver

                    //add last message in map
                    if(chatsMap.containsKey(userStr.toString())){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            chatsMap.replace(userStr.toString(), lastMessage!!)
                        }
                    } else {
                        chatsMap[userStr.toString()] = lastMessage!!
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

                    for ((key, value) in chatsMap) {
                        if (it.key == key){
                            username = (it.getValue(User::class.java) as User).name.toString()

                            if (!chatsMapFinal.containsKey(username)) {
                                chatsMapFinal[username] = value
                            }
                        }
                    }


                }

                for ((key, value) in chatsMapFinal){
                    val res = LastMessage(key, value)
                    currLastChats.add(res)
                }

                val res = mutableListOf<LastMessage>()

                for (i in 0 until currLastChats.size){
                    if (currLastChats[i].name!!.contains(name)){
                        res.add(currLastChats[i])
                    }
                }

                mainPageViewModel.onLastChatsFound(res)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}