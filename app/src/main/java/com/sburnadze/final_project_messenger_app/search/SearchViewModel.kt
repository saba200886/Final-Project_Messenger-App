package com.sburnadze.final_project_messenger_app.search

import androidx.lifecycle.ViewModel
import com.sburnadze.final_project_messenger_app.model.User

class SearchViewModel(private val view: ISearchMainView) : ViewModel(), ISearch {

    private val searchModel = SearchModel(this)

    override fun searchUsers() {
        searchModel.searchUsers()
    }

    override fun searchUserByName(name: String) {
        searchModel.searchUserByName(name)
    }

    override fun onUsersFound(currUsers: List<User>?) {
        view.showFoundUsers(currUsers)
    }


}