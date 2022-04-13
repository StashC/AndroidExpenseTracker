package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

//Count of tabs
private const val NUM_TABS = 3

public class PagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){


    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return Spending()
        }
        return Spending()
    }

}