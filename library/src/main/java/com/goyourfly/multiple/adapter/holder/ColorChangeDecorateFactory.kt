package com.goyourfly.multiple.adapter.holder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter

/**
 * Created by gaoyufei on 2017/6/8.
 */

class ColorChangeDecorateFactory(val colorViewId:Int = 0,
                                 val defaultColor:Int = Color.TRANSPARENT,
                                 val selectColor:Int = Color.LTGRAY): DecorateFactory {

    override fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder {
        return ColorChangeViewHolder(viewHolder.itemView,viewHolder,adapter,colorViewId,defaultColor,selectColor)
    }

}
