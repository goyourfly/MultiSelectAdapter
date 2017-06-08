package com.goyourfly.multiple.adapter.holder

import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter

/**
 * Created by gaoyufei on 2017/6/8.
 */

class ColorViewHolderDecorate(val colorViewId:Int,val defaultColor:Int,val selectColor:Int): ViewHolderDecorate {

    override fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): MultipleViewHolder {
        return ColorViewHolder(viewHolder.itemView,viewHolder,adapter,colorViewId,defaultColor,selectColor)
    }

}
