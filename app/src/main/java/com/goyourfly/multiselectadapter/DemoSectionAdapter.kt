package com.goyourfly.multiselectadapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * Created by gaoyufei on 2017/6/8.
 */
class DemoSectionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TYPE_SECTION  = 1
    val TYPE_CONTENT  = 2

    val list = arrayListOf<String>()

    init {
        for (i in 0..100) {
            addItem("i:$i")
        }
    }

    fun addItem(str: String) {
        list.add(str)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        if (getItemViewType(p1) == TYPE_CONTENT) {
            val holder = p0 as MyViewHolder
            holder.position.text = list[p1]
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if(p1 == TYPE_SECTION){
            return SectionViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_section, p0, false))
        }else{
            return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_email, p0, false))
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        if(position % 10 == 0){
            return TYPE_SECTION
        }
        return TYPE_CONTENT
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view)


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val position: TextView by lazy {
            view.findViewById(R.id.position) as TextView
        }

        init {
            view.setOnClickListener {
                Toast.makeText(view.context, "Hello", Toast.LENGTH_SHORT).show()
            }
            view.findViewById(R.id.image_star)
                    .setOnClickListener {
                        Toast.makeText(view.context, "Star", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}
