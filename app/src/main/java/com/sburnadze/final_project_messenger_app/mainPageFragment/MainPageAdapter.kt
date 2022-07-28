package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.view.ChatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainPageAdapter(private val context: Context?, var list: ArrayList<LastMessage>): RecyclerView.Adapter<ConversationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val currView = LayoutInflater.from(parent.context).inflate(R.layout.main_page_rv_list, parent, false)
        return ConversationViewHolder(currView)
    }


    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val currItem = list[position].message


        holder.name.text = list[position].name
        holder.message.text = currItem.message


      //  val time = getTime(currItem.sentTime)
      //  holder.sentTime.text = time
        holder.sentTime.text = currItem.sentTime


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

    //this function finds time to be written on last message
    private fun getTime(sentTime: String?): String {
        var result = ""

        val currDate = Calendar.getInstance().time
        val sentDate = SimpleDateFormat("dd-MM-yyyy").parse(sentTime)


        val minutes = (currDate.time - sentDate.time)/60000
        val hours = minutes/60
        val days = hours/24

        if(hours < 1){
            result = SimpleDateFormat("mm").parse(minutes.toString()).toString()
        } else if (days < 1){
            result = SimpleDateFormat("hh").parse(hours.toString()).toString()
        } else {
            result = SimpleDateFormat("DD:MM").parse(hours.toString()).toString()
        }



        return result
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