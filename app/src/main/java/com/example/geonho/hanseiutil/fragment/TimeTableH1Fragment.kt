package com.example.geonho.hanseiutil.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.geonho.hanseiutil.R


class TimeTableH1Fragment : Fragment() {

    lateinit var fragmentView:View

    companion object {

        @JvmStatic
        fun newInstance() = TimeTableH1Fragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_time_table_h1, container, false)
        return fragmentView
    }



}
