package com.sburnadze.final_project_messenger_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.view.ChatActivity

class MainPageAdapter(private val context: Context?, var list: ArrayList<LastMessage>): RecyclerView.Adapter<ConversationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val currView = LayoutInflater.from(parent.context).inflate(R.layout.main_page_rv_list, parent, false)
        return ConversationViewHolder(currView)
    }


    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val currItem = list[position]

        holder.name.text = currItem.receiver?.name
        holder.sentTime.text = currItem.sentTime
        holder.message.text = currItem.message


        if (context != null) {
            Glide.with(context).load(currItem.avatar).circleCrop().into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.avatar_image_placeholder)
        }


        //if message item is clicked, chat page should open
        holder.item.setOnClickListener{
            val startChat = Intent(holder.item.context, ChatActivity::class.java)
            holder.item.context.startActivity(startChat)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


class ConversationViewHolder(items: View) : RecyclerView.ViewHolder(items){
    var image = items.findViewById<ImageView>(R.id.main_page_list_imageView)
    var name = items.findViewById<TextView>(R.id.main_page_list_nickname)
    var message = items.findViewById<TextView>(R.id.main_page_list_message)
    var sentTime = items.findViewById<TextView>(R.id.main_page_list_sentTime)

    val item = items
}