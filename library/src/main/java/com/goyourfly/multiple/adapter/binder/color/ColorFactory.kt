package com.goyourfly.multiple.adapter.binder.color

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.binder.BaseViewHolder
import com.goyourfly.multiple.adapter.binder.DecorateFactory

/**
 * Created by gaoyufei on 2017/6/8.
 */

class ColorFactory(val colorViewId:Int = 0,
                   val defaultColor:Int = Color.TRANSPARENT,
                   val selectColor:Int = Color.LTGRAY): DecorateFactory {

    override fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder {
        return ColorViewHolder(viewHolder.itemView,viewHolder,adapter,colorViewId,defaultColor,selectColor)
    }

}
