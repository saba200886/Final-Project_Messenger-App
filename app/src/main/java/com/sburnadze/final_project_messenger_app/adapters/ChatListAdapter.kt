package com.sburnadze.final_project_messenger_app.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import java.text.SimpleDateFormat
import java.util.*

class ChatListAdapter(private val context: Context?, var chat : MutableList<ChatMessage>, private val user: String?):
    RecyclerView.Adapter<ReceiverItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverItemViewHolder {

        val view = if(viewType == RECEIVED) {
            LayoutInflater.from(parent.context).inflate(R.layout.received_message_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item, parent, false)
        }
        return ReceiverItemViewHolder(view)
    }


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ReceiverItemViewHolder, position: Int) {
        holder.text.text = chat[position].message

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val sentDate = sdf.parse(chat[position].sentTime.toString())
        val sdf2 = SimpleDateFormat("hh:mm")
        val date = sdf2.format(sentDate as Date).toString()
        holder.time.text = date

    }

    override fun getItemViewType(position: Int): Int {
        val message = chat[position]
        return if(message.sender.equals(user)){
            SENT
        } else{
            RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return chat.size
    }

    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }

}

class ReceiverItemViewHolder(items: View) : RecyclerView.ViewHolder(items) {
    var text: TextView = items.findViewById(R.id.chat_message_text)
    var time: TextView = items.findViewById(R.id.chat_message_time)

}