package com.sburnadze.final_project_messenger_app.search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.view.ChatActivity

class SearchUserAdapter(
    private val context: Context?,
    var list: ArrayList<User>,
    var currUserId: String
): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val currView = LayoutInflater.from(parent.context).inflate(R.layout.search_rv_list, parent, false)
        return UserViewHolder(currView)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currItem = list[position]
        holder.name.text = currItem.name
        holder.profession.text = currItem.profession

        /* if (context != null) {
             Glide.with(context).load(currItem.avatar).circleCrop().into(holder.image)
         } else {
             holder.image.setImageResource(R.drawable.avatar_image_placeholder)
         }*/


        //if user is clicked, chat page should open
        holder.item.setOnClickListener{
            val startChat = Intent(holder.item.context, ChatActivity::class.java).apply {
                putExtra("secondUserId", currItem.id)
                putExtra("currUserId", currUserId)
            }
            holder.item.context.startActivity(startChat)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


class UserViewHolder(items: View) : RecyclerView.ViewHolder(items){
    var image = items.findViewById<ImageView>(R.id.search_list_imageView)
    var name = items.findViewById<TextView>(R.id.search_list_nickname)
    var profession = items.findViewById<TextView>(R.id.search_list_profession)

    val item = items
}