package com.sburnadze.final_project_messenger_app.search

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.model.User
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity(), ISearchMainView {

    lateinit var usersRv: RecyclerView
    lateinit var users: ArrayList<User>
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchUserAdapter: SearchUserAdapter
    private lateinit var searchField: TextInputEditText

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
        searchUserAdapter = SearchUserAdapter(this, users)
        usersRv.adapter = searchUserAdapter

        searchField = findViewById(R.id.activity_search_search)
        searchUser()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchUser() {
        Log.d("searchUserActivity", "success")
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
        Log.d("findsearchUserActivity", "success")
        searchViewModel.searchUserByName(currText)

    }

    //update view by found user
    @SuppressLint("NotifyDataSetChanged")
    override fun showFoundUsers(currUsers: List<User>?) {
        if(currUsers != null){
            searchUserAdapter.list = users
            searchUserAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Could not find users", Toast.LENGTH_SHORT).show()
            Log.d("Error Message", "Could not find users")
        }
    }

}