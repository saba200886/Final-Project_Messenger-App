package com.sburnadze.final_project_messenger_app

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.view.LoginActivity

class AuthorizationModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()


    @RequiresApi(Build.VERSION_CODES.P)
    fun loginUser(user: String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener(act) { task ->
                if (task.isSuccessful) {
                    Log.d("Authorizationmessage", "signInWithEmail:success")
                    act.startActivity(Intent(act, LoginActivity::class.java))
                } else {
                    Log.w("Authorizationmessage", "signInWithEmail:failure", task.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun registerUser(user: String, pass: String){
        //TODO
    }


}