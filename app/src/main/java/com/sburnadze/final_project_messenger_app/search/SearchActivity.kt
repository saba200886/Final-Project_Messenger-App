package com.sburnadze.final_project_messenger_app.search

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.User
import com.sburnadze.final_project_messenger_app.view.MainPageActivity
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity(), ISearchMainView {

    lateinit var usersRv: RecyclerView
    lateinit var users: ArrayList<User>
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchUserAdapter: SearchUserAdapter
    private lateinit var searchField: TextInputEditText
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initVew()
    }

    override fun onDestroy() {
        //TODO
        super.onDestroy()
    }

    override fun onBackPressed() {
        //TODO
        super.onBackPressed()
    }


    private fun initVew() {
        searchViewModel = SearchViewModel(this)
        users = arrayListOf()

        usersRv = findViewById(R.id.activity_search_rv)

        val currUserId = intent.getStringExtra("currUserId")!!
        searchUserAdapter = SearchUserAdapter(this, users, currUserId)
        usersRv.adapter = searchUserAdapter


        backButton = findViewById(R.id.search_back_icon)
        backButton.setOnClickListener{
            startActivity(Intent(this, MainPageActivity::class.java).apply {
                putExtra("currUserId", currUserId)
            })
        }

        searchField = findViewById(R.id.activity_search_search)
        searchUser()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchUser() {
        RxTextView.textChangeEvents(searchField).debounce(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                runOnUiThread {
                    val currText = searchField.text.toString()
                    users.clear()
                    searchUserAdapter.notifyDataSetChanged()

                    if (currText.length > 2) {
                        findSearchedUser(currText)
                    }
                }
            }
    }


    private fun findSearchedUser(currText: String) {
        searchViewModel.searchUserByName(currText)

    }

    //update view by found user
    @SuppressLint("NotifyDataSetChanged")
    override fun showFoundUsers(currUsers: List<User>?) {
        if(currUsers != null){
            users = currUsers as ArrayList<User>
            searchUserAdapter.list = users
            searchUserAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Could not find users", Toast.LENGTH_SHORT).show()
            Log.d("Error Message", "Could not find users")
        }
    }

}