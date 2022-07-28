package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.model.User

class MainPageFragment(var currUser: String) : Fragment(), IMainPageView {

    lateinit var chatsRv: RecyclerView
    lateinit var chats: ArrayList<LastMessage>
    private lateinit var mainPageAdapter: MainPageAdapter
    private lateinit var mainPageViewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View) {
        chatsRv = view.findViewById(R.id.fragment_main_page_rv)
        chats = arrayListOf()

        mainPageAdapter = MainPageAdapter(this.context, chats)
        chatsRv.adapter = mainPageAdapter


        mainPageViewModel = MainPageViewModel(this, currUser)

        mainPageViewModel.searchLastChats()
    }



    //show all last messages
    @SuppressLint("NotifyDataSetChanged")
    override fun showFoundLastChats(currChats: List<LastMessage>?) {
        if(currChats != null){
            mainPageAdapter.list = currChats as ArrayList<LastMessage>
            mainPageAdapter.notifyDataSetChanged()
        }else {
            Toast.makeText(this.context, "Could not find last messages", Toast.LENGTH_SHORT).show()
            Log.d("Error Message", "Could not find last messages")
        }
    }
}
