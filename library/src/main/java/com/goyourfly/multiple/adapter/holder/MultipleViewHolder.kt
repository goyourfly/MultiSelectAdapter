package com.goyourfly.multiple.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.goyourfly.multiple.adapter.MultipleAdapter
import javax.xml.datatype.Duration

/**
 * Created by gaoyufei on 2017/6/8.
 */
abstract class MultipleViewHolder(val view: View,
                                  val viewHolder: RecyclerView.ViewHolder,
                                  val adapter: MultipleAdapter): RecyclerView.ViewHolder(view) {

    init {
        view.setOnLongClickListener {
            adapter.onItemLongClick(adapterPosition)
        }
        view.setOnClickListener {
            adapter.onItemClick(adapterPosition)
        }
    }

    abstract fun selectStateChanged(state:Int)

    open fun showStateChanged(toState: Int){}

}
