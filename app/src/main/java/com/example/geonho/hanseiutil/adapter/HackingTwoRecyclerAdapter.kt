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

class  HackingTwoRecyclerAdapter(val items :MutableList<String>, val context: Context) : RecyclerView.Adapter<TwoViewHolder>() {

    var count =1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_default_recycler, parent, false)
        return TwoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TwoViewHolder, position: Int) {
        val item = items[position]
        Log.d("Twoitems",item)

        holder.itemView.time.text = "${count}교시"
        holder.itemView.subject.text = items[position]

        count++
    }

}

class TwoViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)