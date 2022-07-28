package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.view.ChatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainPageAdapter(private val context: Context?, var list: ArrayList<LastMessage>): RecyclerView.Adapter<ConversationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val currView = LayoutInflater.from(parent.context).inflate(R.layout.main_page_rv_list, parent, false)
        return ConversationViewHolder(currView)
    }


    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val currItem = list[position].message
        if(currItem?.sender == null)
            return


        holder.name.text = list[position].name
        holder.message.text = currItem.message


        val time = getTime(currItem.sentTime)
        holder.sentTime.text = time
        //holder.sentTime.text = currItem.sentTime


        if (context != null) {
            Glide.with(context).load(currItem.avatar).circleCrop().into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.avatar_image_placeholder)
        }


        //to find current user is sender or receiver
        val fireBaseUser = FirebaseAuth.getInstance().currentUser
        var currUserId = currItem.receiver.toString()
        var secondUserId = currItem.sender.toString()
        if(fireBaseUser?.uid == currItem.sender){
            currUserId = currItem.sender.toString()
            secondUserId = currItem.receiver.toString()
        }

        //if message item is clicked, chat page should open
        holder.item.setOnClickListener{
            val startChat = Intent(holder.item.context, ChatActivity::class.java).apply {
                putExtra("secondUserId", secondUserId)
                putExtra("currUserId", currUserId)
            }
            holder.item.context.startActivity(startChat)
        }
    }

    //this function finds time to be written on last message
    @SuppressLint("SimpleDateFormat")
    private fun getTime(sentTime: String?): String {
        var result = ""


        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val sentDate = sdf.parse(sentTime.toString())



        val minutes = (Calendar.getInstance().time.time - sentDate!!.time)/(60*1000L)
        val hours = minutes/60
        val days = hours/24


        Log.d("asdasdasdMin", minutes.toString())
        Log.d("asdasdasdHour", hours.toString())
        Log.d("asdasdasdDays", days.toString())

        result = when {
            hours < 1 -> {
                "$minutes min"
            }
            days < 1 -> {
                "$hours min"
            }
            else -> {
                val sdf2 = SimpleDateFormat("D MMM")
                sdf2.format(sentDate).toString()
            }
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