package com.example.geonho.hanseiutil.fragment


import android.content.res.AssetManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.adapter.HackingTwoRecyclerAdapter
import com.example.geonho.hanseiutil.network.Time
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_time_table_h2.view.*
import java.io.InputStreamReader


class TimeTableH2Fragment : Fragment() {

    lateinit var fragmentView: View
    private var twoList : MutableList<List<String>> = ArrayList()
    private lateinit var recycleradapter: HackingTwoRecyclerAdapter

    companion object {

        @JvmStatic
        fun newInstance() = TimeTableH2Fragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_time_table_h2, container, false)
        addData()
        return fragmentView
    }

    private fun addData(){
//        val assetManager: AssetManager = resources.assets
//        val source = assetManager.open("TwoTable.json")
//        val reader = InputStreamReader(source)
//        val two: Time = Gson().fromJson(reader, Time::class.java)
        val json:String  = context!!.resources.openRawResource(R.raw.time).bufferedReader().use { it.readText() }
        Log.d("aaa",json)
        val two = Gson().fromJson(json,Time::class.java)
        twoList.add(two.Two.mon)
        twoList.add(two.Two.tue)
        twoList.add(two.Two.wed)
        twoList.add(two.Two.thu)
        twoList.add(two.Two.fri)
        Log.d("gsonResult",twoList.toString())
        setRecyclerView()
        recycleradapter.notifyDataSetChanged()

    }

    private fun setRecyclerView(){
        recycleradapter = HackingTwoRecyclerAdapter(twoList, context!!)
        fragmentView.H2Recyclerview.adapter = recycleradapter
        fragmentView.H2Recyclerview.setHasFixedSize(true)
        fragmentView.H2Recyclerview.layoutManager = LinearLayoutManager(context)
    }

}
