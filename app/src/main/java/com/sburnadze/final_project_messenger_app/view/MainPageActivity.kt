package com.sburnadze.final_project_messenger_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sburnadze.final_project_messenger_app.R

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottomNavigationView = findViewById(R.id.main_page_bottomNavigation)
        bottomNavigationView.background = null

    }
}