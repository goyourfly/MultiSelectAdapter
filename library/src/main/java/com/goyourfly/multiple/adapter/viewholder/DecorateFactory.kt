package com.goyourfly.multiple.adapter.viewholder

import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter

/**
 * Created by gaoyufei on 2017/6/8.
 * 生成选中和未选中状态的View
 */

interface DecorateFactory {

    /**
     * 通过这个方法呢，可以将一个普通的Recycler item 转换为一个支持多选的item
     * 是不是很神奇呀
     */
    fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder;

}
