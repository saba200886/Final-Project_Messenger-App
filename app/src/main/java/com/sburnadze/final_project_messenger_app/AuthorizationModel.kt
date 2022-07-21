package com.sburnadze.final_project_messenger_app

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.view.LoginActivity

class AuthorizationModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()
    private val users = Firebase.database.getReference("users")


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


    fun registerUser(name: String, pass: String, whatIDo: String, image: ImageView){
        firebaseAuth.createUserWithEmailAndPassword(name, pass)
            .addOnCompleteListener(act){task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val userId = user?.uid
                    if (userId != null) {
                        users.child(userId).setValue(User(name, pass, whatIDo, image))
                    }
                    act.startActivity(Intent(act, LoginActivity::class.java))
                } else {
                    Log.w("Authorizationmessage", "signUpWithEmail:failure", task.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }

            }
    }


}