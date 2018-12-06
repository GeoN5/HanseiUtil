package com.example.geonho.hanseiutil.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.geonho.hanseiutil.fragment.TimeTableH1Fragment
import com.example.geonho.hanseiutil.fragment.TimeTableH2Fragment

class TimeTablePagerAdapter(fm: FragmentManager?, val tabCount:Int) : FragmentStatePagerAdapter(fm) {

    val fragment = TimeTableH1Fragment()
    val fragment1 = TimeTableH2Fragment()

    override fun getItem(position: Int): Fragment? {

        // Returning the current tabs
        return when (position) {
            0 -> {
                fragment
            }
            1 -> {
                fragment1
            }
            else -> null
        }

    }

    override fun getCount(): Int {
        return tabCount
    }
}