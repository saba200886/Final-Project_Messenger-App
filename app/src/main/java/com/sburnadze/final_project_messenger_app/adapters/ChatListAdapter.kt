package com.sburnadze.final_project_messenger_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.ChatMessage

class ChatListAdapter(private val context: Context?, var chat : MutableList<ChatMessage>, private val user: String?):
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view = if(viewType == RECEIVED) {
            LayoutInflater.from(parent.context).inflate(R.layout.received_message_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item, parent, false)
        }
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.text.text = chat[position].message
        holder.time.text = chat[position].sentTime
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


class ItemViewHolder(items: View) : RecyclerView.ViewHolder(items) {
    var text: TextView = items.findViewById(R.id.received_text)
    var time: TextView = items.findViewById(R.id.received_time)

}
