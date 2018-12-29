package com.example.geonho.hanseiutil.fragment


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
import java.util.*


class TimeTableH2Fragment : Fragment() {

    private lateinit var fragmentView: View
    private var twoList : MutableList<String> = ArrayList()
    private lateinit var recycleradapter: HackingTwoRecyclerAdapter
    val day = Calendar.getInstance()!!.get(Calendar.DAY_OF_WEEK)

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
        val json:String  = context!!.resources.openRawResource(R.raw.time).bufferedReader().use { it.readText() }
        val two = Gson().fromJson(json,Time::class.java)
        Log.d("twoGson",two.toString())

        when (day) {
            2 -> {
                twoList.addAll(two.Two.mon)
                Log.d("two_mon",twoList.toString())
            }
            3 -> {
                twoList.addAll(two.Two.tue)
                Log.d("two_tue",twoList.toString())
            }
            4 -> {
                twoList.addAll(two.Two.wed)
                Log.d("two_wed",twoList.toString())
            }
            5 -> {
                twoList.addAll(two.Two.thu)
                Log.d("two_thu",twoList.toString())
            }
            6 -> {
                twoList.addAll(two.Two.fri)
                Log.d("two_fri",twoList.toString())
            }
        }

        Log.d("twoList",twoList.toString())
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