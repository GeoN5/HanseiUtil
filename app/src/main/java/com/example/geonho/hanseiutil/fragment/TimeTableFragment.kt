package com.example.geonho.hanseiutil.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.adapter.TimeTablePagerAdapter
import kotlinx.android.synthetic.main.fragment_time_table.view.*
import java.util.*


class TimeTableFragment : Fragment() {

    lateinit var fragmentView:View

    companion object {
        @JvmStatic
        fun newInstance() = TimeTableFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_time_table, container, false)
        setTablayout()
        setViewpager()
        weekCheck()
        return fragmentView
    }

    private fun setTablayout(){
        fragmentView.tab.addTab(fragmentView.tab.newTab().setText("H2-1"))
        fragmentView.tab.addTab(fragmentView.tab.newTab().setText("H2-2"))
    }

    private fun setViewpager(){
        val pagerAdapter = TimeTablePagerAdapter(fragmentManager, fragmentView.tab.tabCount)
        fragmentView.viewPager.adapter = pagerAdapter
        fragmentView.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(fragmentView.tab))

        fragmentView.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                fragmentView.viewPager.currentItem = tab!!.position
            }

        })

    }

    private fun weekCheck() {
        val day = Calendar.getInstance()!!.get(Calendar.DAY_OF_WEEK)
        if (day > 6) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
                .setTitle("Today").setMessage("오늘은 주말입니다.").setPositiveButton("확인")
                { dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()
            dialog.show()
        }
    }

}