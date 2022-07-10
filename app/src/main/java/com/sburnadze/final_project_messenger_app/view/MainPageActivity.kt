package com.sburnadze.final_project_messenger_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.adapters.ViewPagerAdapter

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        initView()
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.main_page_bottomNavigation)
        bottomNavigationView.background = null

        viewPager = findViewById(R.id.main_page_fragmentViewer)
        viewPager.adapter = ViewPagerAdapter(this)

        Log.d("asdasd", "first")
        //main page should open by default
        viewPager.currentItem = 1
        Log.d("asdasd", "second")

        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.menu_settings)
                viewPager.currentItem = 1
            else if(it.itemId == R.id.menu_home)
                viewPager.currentItem = 0

            return@setOnItemSelectedListener true
        }
    }
}