package com.sburnadze.final_project_messenger_app

interface IAuthorization {

    fun login(user: String, pass: String)

    fun register(user: String, pass: String)
}
