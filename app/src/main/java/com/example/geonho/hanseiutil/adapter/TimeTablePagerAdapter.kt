package com.example.geonho.hanseiutil.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.geonho.hanseiutil.fragment.TimeTableH1Fragment
import com.example.geonho.hanseiutil.fragment.TimeTableH2Fragment

class TimeTablePagerAdapter(fm: FragmentManager?, private val tabCount:Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> {
                TimeTableH1Fragment.newInstance()
            }
            1 -> {
                TimeTableH2Fragment.newInstance()
            }
            else -> null
        }

    }

    override fun getCount(): Int {
        return tabCount
    }
}