package com.example.geonho.hanseiutil.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.geonho.hanseiutil.R


class TimeTableFragment : Fragment() {

    lateinit var fragmentView:View

    companion object {

        @JvmStatic
        fun newInstance() = TimeTableFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_time_table, container, false)
        return fragmentView
    }



}
