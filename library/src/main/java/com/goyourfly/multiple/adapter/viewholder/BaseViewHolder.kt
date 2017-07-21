package com.goyourfly.multiple.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.goyourfly.multiple.adapter.MultipleAdapter

/**
 * Created by gaoyufei on 2017/6/8.
 */
abstract class BaseViewHolder(val root: View,
                              val viewHolder: RecyclerView.ViewHolder,
                              val adapter: MultipleAdapter) : RecyclerView.ViewHolder(EventObserverView(root.context,adapter,root)) {

    init {
        if(itemView is EventObserverView){
            itemView.setLongClickCallback {
                adapter.onItemLongClick(viewHolder.adapterPosition)
            }
            itemView.setSelectStateClickCallback {
                adapter.onItemClick(viewHolder.adapterPosition)
            }
        }
    }

    abstract fun selectStateChanged(state: Int)

    open fun showStateChanged(toState: Int) {}

}
