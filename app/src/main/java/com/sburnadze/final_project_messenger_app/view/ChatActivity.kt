package com.sburnadze.final_project_messenger_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sburnadze.final_project_messenger_app.ChatModel
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.adapters.ChatListAdapter
import com.sburnadze.final_project_messenger_app.model.ChatMessage

class ChatActivity : AppCompatActivity() {
    private lateinit var chatViewModel: ChatModel
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var chat: MutableList<ChatMessage>
    private lateinit var currId: String
    private lateinit var secondId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        init()
    }

    private fun init(){
        editText = findViewById(R.id.message)
        val currUser = intent.getSerializableExtra("currUserId")
        currId = currUser.toString()
        val secondUser = intent.getSerializableExtra("secondUserId")
        secondId = secondUser.toString()
        chatViewModel = ChatModel(this)

        showMessages()

        findViewById<Button>(R.id.back_icon).setOnClickListener{
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<Button>(R.id.send_button).setOnClickListener{
            val message = editText.text.toString()
            if(message.isEmpty()){
                Toast.makeText(this,"write message", Toast.LENGTH_SHORT).show()
            } else {
                chatViewModel.sendMessage(message, currId, secondId)
            }
            editText.setText("")
        }

    }

    private fun showMessages(){
        recyclerView = findViewById(R.id.chat_recycler_view)
        chat = mutableListOf()
        chatViewModel.loadMessages(chat, currId, secondId)
        chatListAdapter = ChatListAdapter(this, chat, currId)
        recyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.adapter = chatListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}