package com.goyourfly.multiple.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.SelectState

/**
 * Created by gaoyufei on 2017/6/8.
 */
class ColorViewHolder(view: View,
                      viewHolder: RecyclerView.ViewHolder,
                      adapter: MultipleAdapter,
                      val colorViewId: Int,
                      val defaultColor: Int,
                      val selectColor: Int) : MultipleViewHolder(view, viewHolder, adapter) {


    val colorView: View by lazy {
        if (colorViewId == 0)
            throw IllegalArgumentException("Specific id is 0")
        itemView.findViewById(colorViewId)
    }


    override fun selectStateChanged(state: Int) {
        if(state == SelectState.UN_SELECT){
            colorView?.setBackgroundColor(defaultColor)
        }else if(state == SelectState.SELECT){
            colorView?.setBackgroundColor(selectColor)
        }
    }
}
