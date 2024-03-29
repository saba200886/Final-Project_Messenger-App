package com.sburnadze.final_project_messenger_app.mainPageFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.LastMessage
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainPageFragment(var currUser: String) : Fragment(), IMainPageView {

    lateinit var chatsRv: RecyclerView
    lateinit var chats: ArrayList<LastMessage>
    private lateinit var mainPageAdapter: MainPageAdapter
    private lateinit var mainPageViewModel: MainPageViewModel
    lateinit var searchView: TextInputEditText

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

        mainPageViewModel.searchLastChats("")

        searchView = view.findViewById(R.id.fragment_main_page_search)
        searchUser()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchUser() {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        RxTextView.textChangeEvents(searchView).debounce(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                executor.execute {
                    val currText = searchView.text.toString()

                    handler.post{
                        mainPageViewModel.searchLastChats(currText)
                    }
                }
            }
    }


    //show all last messages
    @SuppressLint("NotifyDataSetChanged")
    override fun showFoundLastChats(currChats: List<LastMessage>?) {
        chats = currChats as ArrayList<LastMessage>

        mainPageAdapter.list = chats
        mainPageAdapter.notifyDataSetChanged()
    }

}
