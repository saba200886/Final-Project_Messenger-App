package com.sburnadze.final_project_messenger_app.search

import com.sburnadze.final_project_messenger_app.model.User

interface ISearchMainView {

    fun showFoundUsers(currUsers: List<User>?)
}