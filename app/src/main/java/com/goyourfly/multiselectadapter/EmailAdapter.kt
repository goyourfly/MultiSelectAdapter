package com.goyourfly.multiselectadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by gaoyufei on 2017/6/8.
 */
class EmailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list = arrayListOf<String>()

    init {
        for (i in 0..100){
            addItem("i:$i")
        }
    }

    fun addItem(str:String){
        list.add(str)
    }

    fun deleteItem(position:Int){
        list.removeAt(position)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        val holder = p0 as MyViewHolder
        holder.position.text = list[p1]
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_email,p0,false))
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val position:TextView by lazy {
            view.findViewById(R.id.position) as TextView
        }
    }
}
