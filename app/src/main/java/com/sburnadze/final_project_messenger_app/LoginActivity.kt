package com.sburnadze.final_project_messenger_app

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


       /* val database = Firebase.database
        val myRef = database.getReference("message")
        val userReference = database.getReference("UserList")


        myRef.setValue("Hello, World1!")

        userReference.push().key?.let{
            userReference.child(it).setValue(User("saba","saba1234", "auditor"))
        }



        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "count is:" + dataSnapshot.children.count())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
*/

    }
}
