package com.sburnadze.final_project_messenger_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.adapters.MainPageAdapter
import com.sburnadze.final_project_messenger_app.model.LastMessage
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.search.SearchUserAdapter

class MainPageFragment : Fragment() {

    lateinit var chatsRv: RecyclerView
    lateinit var chats: ArrayList<LastMessage>
    private lateinit var mainPageAdapter: MainPageAdapter

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
    }
}
