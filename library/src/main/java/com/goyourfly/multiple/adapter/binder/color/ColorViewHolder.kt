package com.goyourfly.multiple.adapter.binder.color

import android.support.v7.widget.RecyclerView
import android.view.View
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.SelectState

/**
 * Created by gaoyufei on 2017/6/8.
 */
class ColorViewHolder(view: android.view.View,
                      viewHolder: android.support.v7.widget.RecyclerView.ViewHolder,
                      adapter: com.goyourfly.multiple.adapter.MultipleAdapter,
                      val colorViewId: Int,
                      val defaultColor: Int,
                      val selectColor: Int) : com.goyourfly.multiple.adapter.binder.BaseViewHolder(view, viewHolder, adapter) {


    val colorView: android.view.View by lazy {
        if (colorViewId == 0)
            view
        else
            itemView.findViewById(colorViewId)
    }


    override fun selectStateChanged(state: Int) {
        if (state == com.goyourfly.multiple.adapter.SelectState.UN_SELECT) {
            colorView.setBackgroundColor(defaultColor)
        } else if (state == com.goyourfly.multiple.adapter.SelectState.SELECT) {
            colorView.setBackgroundColor(selectColor)
        }
    }
}
