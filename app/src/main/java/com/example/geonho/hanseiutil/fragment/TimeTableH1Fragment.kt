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
import com.example.geonho.hanseiutil.adapter.HackingOneRecyclerAdapter
import com.example.geonho.hanseiutil.network.Time
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_time_table_h1.view.*
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class TimeTableH1Fragment : Fragment() {

    lateinit var fragmentView:View
    private var oneList : MutableList<List<String>> = ArrayList()
    private lateinit var recycleradapter: HackingOneRecyclerAdapter
    lateinit var json:String

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
        val assetManager: AssetManager = resources.assets
        val source: InputStream = assetManager.open("TimeTable.json")
        val reader:InputStreamReader = InputStreamReader(source)
        val one: Time = Gson().fromJson(reader,Time::class.java)
//        2.try {
//            val inputStream = resources.assets.open("TimeTable.json")
//            val size:Int = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            json = String(buffer)
//            Log.d("aaa",buffer.toString())
//        }catch (e: IOException){
//            e.printStackTrace()
//        }
//        val one = Gson().fromJson(json,Time::class.java)
//        3.val json:String  = context!!.resources.openRawResource(R.raw.time).bufferedReader().use { it.readText() }
//        Log.d("aaa",json)
//        val one = Gson().fromJson(json,Time::class.java)
        Log.d("gsonResult",one.toString())
        oneList.add(one.One.mon)
        oneList.add(one.One.tue)
        oneList.add(one.One.wed)
        oneList.add(one.One.thu)
        oneList.add(one.One.fri)
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
