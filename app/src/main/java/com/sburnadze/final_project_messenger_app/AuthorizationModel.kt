package com.sburnadze.final_project_messenger_app

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.view.LoginActivity
import com.sburnadze.final_project_messenger_app.view.MainPageActivity
import com.sburnadze.final_project_messenger_app.view.ProfilePageFragment

class AuthorizationModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()
    private val users = Firebase.database.getReference("users")


    @RequiresApi(Build.VERSION_CODES.P)
    fun loginUser(user: String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener(act) { task ->
                if (task.isSuccessful) {
                    Log.d("Authorizationmessage", "signInWithEmail:success")
                    act.startActivity(Intent(act, MainPageActivity::class.java))
                } else {
                    Log.w("Authorizationmessage", "signInWithEmail:failure", task.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun registerUser(name: String, pass: String, whatIDo: String){
        //val user = User(name, pass, whatIDo, image, name)
        //users.child(name).setValue(user)
        firebaseAuth.createUserWithEmailAndPassword(name, pass)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d("registerdd", "success")
                    val user = firebaseAuth.currentUser
                    val userId = user?.uid
                    if (userId != null) {
                        val user = User(name, pass, whatIDo, userId)
                        users.child(userId).setValue(user)
                    }
                    act.startActivity(Intent(act, MainPageActivity::class.java))
                } else {
                    Log.w("Authorizationmessage", "signUpWithEmail:failure", it.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }

            }
    }


}