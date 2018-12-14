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


class  HackingOneRecyclerAdapter(val items : MutableList<String>, val context: Context) : RecyclerView.Adapter<OneViewHolder>() {

    private var count =1

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
        Log.d("Oneitems",item)

        holder.itemView.time.text = "${count}교시"
        holder.itemView.subject.text = items[position]

        count++
    }

}

class OneViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)