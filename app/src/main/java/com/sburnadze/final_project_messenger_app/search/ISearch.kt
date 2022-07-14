package com.sburnadze.final_project_messenger_app.search

import com.sburnadze.final_project_messenger_app.model.User

interface ISearch {

    fun searchUsers()

    fun searchUserByName(name: String)

    fun onUsersFound(currUsers: List<User>?)
}