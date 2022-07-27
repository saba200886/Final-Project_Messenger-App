package com.sburnadze.final_project_messenger_app.search

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sburnadze.final_project_messenger_app.model.User

class SearchModel(private val searchViewModel: SearchViewModel) {

    private val users = Firebase.database.getReference("users")

    //this function searches for all users registered
    fun searchUsers(){
        users.get().addOnSuccessListener { ifSuccessful(it) }
            .addOnFailureListener { ifFailed(it) }
    }


    //this function searches for users by name
    fun searchUserByName(name: String){
        Log.d("searchUserbymodel", "success")
        users.orderByChild("name")
            .equalTo(name)
            .get()
            .addOnSuccessListener { ifSuccessful(it) }
            .addOnFailureListener { ifFailed(it) }
    }


    private fun ifSuccessful(it: DataSnapshot?) {
        Log.d("searchUserIf", "success")
        Log.d("Search Error", "jurxa@gmail.com  finding users")
        val currUsers = mutableListOf<User>()
        it?.children?.forEach{
            val currUser = it.getValue(User::class.java) as User
            //TODO users info should be saved with user

            currUsers.add(currUser)
        }

        searchViewModel.onUsersFound(currUsers)
    }


    private fun ifFailed(it: Exception) {
        Log.d("searchUserelse", "success")
        Log.d("Search Error", "Error occurred in finding users", it)
        searchViewModel.onUsersFound(null)
    }


}