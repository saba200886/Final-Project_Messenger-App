package com.sburnadze.final_project_messenger_app.mainPageFragment

import androidx.lifecycle.ViewModel
import com.sburnadze.final_project_messenger_app.model.ChatMessage
import com.sburnadze.final_project_messenger_app.model.LastMessage


class MainPageViewModel(private val view: IMainPageView, var currUser: String) : ViewModel(), IMainPage  {

    private val mainPageModel = MainPageFragmentModel(this)

    override fun searchLastChats() {
        mainPageModel.searchLastChats(currUser)
    }


    override fun onLastChatsFound(currLastChats: List<ChatMessage>?) {
        view.showFoundLastChats(currLastChats)
    }


}