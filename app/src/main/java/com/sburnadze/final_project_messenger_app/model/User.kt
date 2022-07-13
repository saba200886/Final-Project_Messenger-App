package com.sburnadze.final_project_messenger_app.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val name: String? = null, val password: String? = null, val profession: String? = null,
                val avatar: String? = null, val id: String?)
