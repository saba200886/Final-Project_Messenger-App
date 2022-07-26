package com.sburnadze.final_project_messenger_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sburnadze.final_project_messenger_app.view.MainPageFragment
import com.sburnadze.final_project_messenger_app.view.ProfilePageFragment

class ViewPagerAdapter (activity: FragmentActivity): FragmentStateAdapter(activity) {

    var frags = arrayListOf(ProfilePageFragment(), MainPageFragment())

    override fun getItemCount(): Int {
        return frags.size
    }

    override fun createFragment(position: Int): Fragment {
        return frags[position]
    }

}