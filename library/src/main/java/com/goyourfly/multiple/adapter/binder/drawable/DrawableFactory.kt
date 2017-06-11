package com.goyourfly.multiple.adapter.binder.color

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.binder.BaseViewHolder
import com.goyourfly.multiple.adapter.binder.DecorateFactory

/**
 * Created by gaoyufei on 2017/6/8.
 */

class DrawableFactory(val colorViewId:Int = 0,
                                 val default:Drawable,
                                 val select:Drawable): DecorateFactory {

    override fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder {
        return DrawableViewHolder(viewHolder.itemView,viewHolder,adapter,colorViewId,default,select)
    }

}
