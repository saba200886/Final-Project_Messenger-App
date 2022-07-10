package com.sburnadze.final_project_messenger_app.model

data class LastMessage(val receiver: User? = null, val message: String? = null,
                       val sentTime: String? = null, val avatar: String? = null)
