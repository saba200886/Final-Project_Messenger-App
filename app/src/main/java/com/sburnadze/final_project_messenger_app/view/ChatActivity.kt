package com.sburnadze.final_project_messenger_app.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.ChatModel
import com.sburnadze.final_project_messenger_app.IChatMainView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.adapters.ChatListAdapter
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity(), IChatMainView {
    private lateinit var chatViewModel: ChatModel
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var chat: MutableList<ChatMessage>
    private lateinit var currId: String
    private lateinit var secondId: String
    private lateinit var image: ImageView
    private lateinit var chatName: TextView
    private lateinit var chatProfession: TextView


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
        chatViewModel = ChatModel(ChatViewModel(this))

        showMessages()
        findViewById<ImageView>(R.id.back_icon).bringToFront()
        findViewById<ImageView>(R.id.back_icon).setOnClickListener{
            startActivity(Intent(this, MainPageActivity::class.java).apply {
                putExtra("currUserId", currId)
            })
        }

        findViewById<AppCompatImageButton>(R.id.send_button).setOnClickListener{
            val message = editText.text.toString()
            if(message.isEmpty()){
                Toast.makeText(this,"write message", Toast.LENGTH_SHORT).show()
            } else {
                chatViewModel.sendMessage(message, currId, secondId)
            }
            editText.setText("")
        }

        chatName = findViewById(R.id.chat_username)
        chatProfession = findViewById(R.id.chat_what_i_do)
        showChatUser()
    }

    private fun showChatUser(){
        val users = Firebase.database.getReference("users")

        users.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val currentUser = it.getValue(User::class.java) as User
                    if(currentUser.id == secondId){
                        chatName.text = currentUser.name.toString()
                        chatProfession.text = currentUser.profession.toString()

                        return
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Search Error", "Error occurred in finding user", error.toException())
            }

        })
    }


    private fun showMessages(){
        image = findViewById(R.id.image)
        recyclerView = findViewById(R.id.chat_recycler_view)
        chat = mutableListOf()
        chatViewModel.loadMessages(chat, currId, secondId)
        chatListAdapter = ChatListAdapter(this, chat, currId)
        recyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.adapter = chatListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun showFoundMessages(currMessages: List<ChatMessage>?) {
        if(currMessages != null){
            chatListAdapter.chat = currMessages as ArrayList<ChatMessage>
            chatListAdapter.notifyDataSetChanged()
        }
    }
}