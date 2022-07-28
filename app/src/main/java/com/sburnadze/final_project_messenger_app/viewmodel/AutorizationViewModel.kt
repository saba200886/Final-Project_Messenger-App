package com.sburnadze.final_project_messenger_app.viewmodel

import android.app.Activity
import android.os.Build
import androidx.lifecycle.ViewModel
import com.sburnadze.final_project_messenger_app.AuthorizationModel
import com.sburnadze.final_project_messenger_app.IAuthorization
import com.sburnadze.final_project_messenger_app.model.User

class AutorizationViewModel(act: Activity): ViewModel(), IAuthorization {
    private val autorizationModel = AuthorizationModel(act)

    override fun login(user: String, pass: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            autorizationModel.loginUser(user, pass)
        }
    }

    override fun register(user: String, pass: String, whatIDo: String) {
        autorizationModel.registerUser(user, pass, whatIDo)
    }

    override fun signOut() {
        autorizationModel.signOut()
    }

    override fun update(name: String, whatIDo: String) {
        autorizationModel.update(name, whatIDo)
    }


}
