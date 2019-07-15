package com.goyourfly.multiselectadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * Created by gaoyufei on 2017/6/8.
 */
class DemoAdapter : RecyclerView.Adapter<DemoAdapter.MyViewHolder>() {

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val holder = holder as MyViewHolder
        holder.position.text = list[position]
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_email, p0, false))
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val position: TextView by lazy {
            view.findViewById(R.id.position) as TextView
        }

        init {
            view.setOnClickListener {
                Toast.makeText(view.context, "Hello", Toast.LENGTH_SHORT).show()
            }
            view.findViewById<View>(R.id.image_star)
                    .setOnClickListener {
                        Toast.makeText(view.context, "Star", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}
