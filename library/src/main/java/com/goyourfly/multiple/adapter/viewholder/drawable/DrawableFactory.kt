package com.goyourfly.multiple.adapter.viewholder.color

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder
import com.goyourfly.multiple.adapter.viewholder.DecorateFactory

/**
 * Created by gaoyufei on 2017/6/8.
 */

class DrawableFactory(val colorViewId:Int,
                                 val default:Drawable,
                                 val select:Drawable): DecorateFactory {

    override fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder {
        return DrawableViewHolder(viewHolder.itemView,viewHolder,adapter,colorViewId,default,select)
    }

}
