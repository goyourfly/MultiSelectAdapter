package com.goyourfly.multiple.adapter.holder

import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter

/**
 * Created by gaoyufei on 2017/6/8.
 * 生成选中和未选中状态的View
 */

interface DecorateFactory {

    fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): BaseViewHolder;

}
