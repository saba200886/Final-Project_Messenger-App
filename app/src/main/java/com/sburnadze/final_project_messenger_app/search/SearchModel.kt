package com.sburnadze.final_project_messenger_app.search

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
        val currUsers = mutableListOf<User>()
/*        users.orderByChild("name")
            .startAt(name)
            .endAt(name + "\uf8ff")
            .get()
            .addOnSuccessListener { ifSuccessful(it) }
            .addOnFailureListener { ifFailed(it) }*/
        users.orderByChild("name")
            .startAt(name)
            .endAt(name + "\uf8ff")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach{
                        val currUser = it.getValue(User::class.java) as User
                        currUser.id = it.key

                        currUsers.add(currUser)
                    }

                    searchViewModel.onUsersFound(currUsers)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Search Error", "Error occurred in finding users", error.toException())
                }

            })
    }


    private fun ifSuccessful(it: DataSnapshot?) {
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
        Log.d("Search Error", "Error occurred in finding users", it)
        searchViewModel.onUsersFound(null)
    }


}