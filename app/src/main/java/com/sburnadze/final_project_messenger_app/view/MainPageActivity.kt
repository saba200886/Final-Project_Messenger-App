package com.sburnadze.final_project_messenger_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sburnadze.final_project_messenger_app.R
import com.sburnadze.final_project_messenger_app.adapters.ViewPagerAdapter
import com.sburnadze.final_project_messenger_app.mainPageFragment.MainPageFragment
import com.sburnadze.final_project_messenger_app.search.SearchActivity

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager2
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var currUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        initView()
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.main_page_bottomNavigation)
        bottomNavigationView.background = null

        floatingButton = findViewById(R.id.floating_button)

        val currUser = intent.getStringExtra("currUserId").toString()
        floatingButton.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java).apply {
                putExtra("currUserId", currUser)
            })
        }

        val frags = arrayListOf(ProfilePageFragment(currUser), MainPageFragment(currUser))

        viewPager = findViewById(R.id.main_page_fragmentViewer)
        viewPager.adapter = ViewPagerAdapter(this, frags)


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