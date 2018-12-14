package com.example.geonho.hanseiutil.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.adapter.HackingOneRecyclerAdapter
import com.example.geonho.hanseiutil.network.Time
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_time_table_h1.view.*
import java.util.*


class TimeTableH1Fragment : Fragment() {

    private lateinit var fragmentView:View
    private var oneList : MutableList<String> = ArrayList()
    private lateinit var recycleradapter: HackingOneRecyclerAdapter
    val day = Calendar.getInstance()!!.get(Calendar.DAY_OF_WEEK)

    companion object {
        @JvmStatic
        fun newInstance() = TimeTableH1Fragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView =  inflater.inflate(R.layout.fragment_time_table_h1, container, false)
        addData()
        return fragmentView
    }

    private fun addData(){
        val json:String  = context!!.resources.openRawResource(R.raw.time).bufferedReader().use {
            it.readText() }
        val one = Gson().fromJson(json,Time::class.java)
        Log.d("oneGson",one.toString())

        when (day) {
            2 -> {
                oneList.addAll(one.One.mon)
                Log.d("one_mon",oneList.toString())
            }
            3 -> {
                oneList.addAll(one.One.tue)
                Log.d("one-tue",oneList.toString())
            }
            4 -> {
                oneList.addAll(one.One.wed)
                Log.d("one_wed",oneList.toString())
            }
            5 -> {
                oneList.addAll(one.One.thu)
                Log.d("one_thu",oneList.toString())
            }
            6 -> {
                oneList.addAll(one.One.fri)
                Log.d("one_fri",oneList.toString())
            }
            else -> {
                Toast.makeText(context,"오늘은 주말입니다!",Toast.LENGTH_LONG).show()
            }
        }

        Log.d("oneList",oneList.toString())
        setRecyclerView()
        recycleradapter.notifyDataSetChanged()

    }

    private fun setRecyclerView(){
        recycleradapter = HackingOneRecyclerAdapter(oneList, context!!)
        fragmentView.H1Recyclerview.adapter = recycleradapter
        fragmentView.H1Recyclerview.setHasFixedSize(true)
        fragmentView.H1Recyclerview.layoutManager = LinearLayoutManager(context)
    }

}
