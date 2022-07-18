package com.sburnadze.final_project_messenger_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.sburnadze.final_project_messenger_app.ChatModel
import com.sburnadze.final_project_messenger_app.IChat
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var chatViewModel: ChatModel
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        init()
    }

    private fun init(){
        editText = findViewById(R.id.message)
        val currUser = intent.getSerializableExtra("currUserId")
        val currId = currUser.toString()
        val secondUser = intent.getSerializableExtra("secondUserId")
        val secondId = secondUser.toString()
        chatViewModel = ChatModel(this)


        findViewById<Button>(R.id.back_icon).setOnClickListener{
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<Button>(R.id.send_button).setOnClickListener{
           chatViewModel.sendMessage(editText.text.toString(), currId, secondId)
        }

    }
}