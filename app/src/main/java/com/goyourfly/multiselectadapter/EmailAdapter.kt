package com.goyourfly.multiselectadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by gaoyufei on 2017/6/8.
 */
class EmailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_email,p0,false))
    }


    override fun getItemCount(): Int {
        return 100
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}
