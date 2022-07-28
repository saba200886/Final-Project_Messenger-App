package com.sburnadze.final_project_messenger_app

import android.net.Uri

interface IProfile {

    fun uploadImage(name: String, uri: Uri)
}