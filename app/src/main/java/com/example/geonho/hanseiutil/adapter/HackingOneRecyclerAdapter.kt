package com.example.geonho.hanseiutil.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geonho.hanseiutil.R
import kotlinx.android.synthetic.main.item_default_recycler.view.*
import java.util.*


class  HackingOneRecyclerAdapter(val items : MutableList<List<String>>, val context: Context) : RecyclerView.Adapter<OneViewHolder>() {

    var count =1
    var index = 0
    val day = Calendar.getInstance()!!.get(Calendar.DAY_OF_WEEK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_default_recycler, parent, false)
        return OneViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OneViewHolder, position: Int) {
        val item = items[position]
        Log.d("asdf",item.component1().toString())


        when(day){
            2 ->{
                holder.itemView.time.text = "${count}교시"
                holder.itemView.subject.text = item.component1()[index].toString()
            }
            3 ->{
                holder.itemView.time.text = "${count}교시"
                holder.itemView.subject.text = item.component2()[index].toString()
            }
            4 ->{
                holder.itemView.time.text = "${count}교시"
                holder.itemView.subject.text = item.component3()[index].toString()
            }
            5 ->{
                holder.itemView.time.text = "${count}교시"
                holder.itemView.subject.text = item.component4()[index].toString()
            }
            6 ->{
                holder.itemView.time.text = "${count}교시"
                holder.itemView.subject.text = item.component5()[index].toString()
            }
            else -> {
                holder.itemView.time.text = "오늘은"
                holder.itemView.subject.text = "주말입니다."
            }
        }

        count++
        index++
    }
}

class OneViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)