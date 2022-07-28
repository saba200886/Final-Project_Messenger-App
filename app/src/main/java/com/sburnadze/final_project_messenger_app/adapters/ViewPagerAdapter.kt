package com.sburnadze.final_project_messenger_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sburnadze.final_project_messenger_app.mainPageFragment.MainPageFragment
import com.sburnadze.final_project_messenger_app.view.ProfilePageFragment

class ViewPagerAdapter(activity: FragmentActivity, var frags: ArrayList<Fragment>): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return frags.size
    }

    override fun createFragment(position: Int): Fragment {
        return frags[position]
    }

}