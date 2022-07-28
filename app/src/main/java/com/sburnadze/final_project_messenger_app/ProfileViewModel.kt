package com.sburnadze.final_project_messenger_app

import android.net.Uri
import androidx.lifecycle.ViewModel

class ProfileViewModel():ViewModel(), IProfile {

    private val profileModel = ProfileModel()

    override fun uploadImage(name: String, uri: Uri) {
        profileModel.uploadImage(name, uri)
    }

}