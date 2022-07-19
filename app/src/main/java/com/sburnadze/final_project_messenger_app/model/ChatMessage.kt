package com.sburnadze.final_project_messenger_app.model

data class ChatMessage(val sender: String? = null, val receiver: String? = null, val message: String? = null,
                       val sentTime: String? = null, val avatar: String? = null)