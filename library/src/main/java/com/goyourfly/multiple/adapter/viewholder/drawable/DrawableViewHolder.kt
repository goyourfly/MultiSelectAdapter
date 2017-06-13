package com.goyourfly.multiple.adapter.viewholder.color

import android.graphics.drawable.Drawable
import com.goyourfly.multiple.adapter.SelectState
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder

/**
 * Created by gaoyufei on 2017/6/8.
 */
class DrawableViewHolder(view: android.view.View,
                            viewHolder: android.support.v7.widget.RecyclerView.ViewHolder,
                            adapter: com.goyourfly.multiple.adapter.MultipleAdapter,
                            val targetId: Int,
                            val normal: Drawable,
                            val select: Drawable) : BaseViewHolder(view, viewHolder, adapter) {


    val colorView: android.view.View by lazy {
        if (targetId == 0)
            view
        else
            itemView.findViewById(targetId)
    }


    override fun selectStateChanged(state: Int) {
        if (state == SelectState.UN_SELECT) {
            colorView.setBackgroundDrawable(normal)
        } else if (state == SelectState.SELECT) {
            colorView.setBackgroundDrawable(select)
        }
    }
}
