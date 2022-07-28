package com.sburnadze.final_project_messenger_app

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.view.MainPageActivity

class AuthorizationModel(private val act: Activity) {
    private var firebaseAuth = FirebaseAuth.getInstance()
    private val users = Firebase.database.getReference("users")


    @RequiresApi(Build.VERSION_CODES.P)
    fun loginUser(user: String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener(act) { task ->
                if (task.isSuccessful) {
                    Log.d("Authorizationmessage", "signInWithEmail:success")
                    act.startActivity(Intent(act, MainPageActivity::class.java).apply {
                        putExtra("currUserId", firebaseAuth.currentUser?.uid)
                    })
                } else {
                    Log.w("Authorizationmessage", "signInWithEmail:failure", task.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun registerUser(name: String, pass: String, whatIDo: String){
        firebaseAuth.createUserWithEmailAndPassword(name, pass)
            .addOnCompleteListener(act){task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val userId = user?.uid
                    if (userId != null) {
                        users.child(userId).setValue(User(name, whatIDo, userId))
                    }
                    act.startActivity(Intent(act, MainPageActivity::class.java).apply {
                        putExtra("currUserId", userId)
                    })
                } else {
                    Log.w("Authorizationmessage", "signUpWithEmail:failure", task.exception)
                    Toast.makeText(act,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }

            }
    }

    fun signOut(){
        firebaseAuth.signOut()
    }

    fun update(name: String, whatIDo: String){
        val userId = firebaseAuth.currentUser!!.uid
        users.child(userId).setValue(User(name, whatIDo, userId))
    }


}