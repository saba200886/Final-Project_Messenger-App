package com.sburnadze.final_project_messenger_app

import android.net.Uri
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ProfileModel() {

    private val storage = Firebase.storage.reference

    fun uploadImage(name: String, uri: Uri){
        val images = storage.child("images/$name")

        images.putFile(uri)
    }
}