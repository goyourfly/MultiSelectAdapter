package com.goyourfly.multiple.adapter.holder

import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.holder.MultipleViewHolder

/**
 * Created by gaoyufei on 2017/6/8.
 * 生成选中和未选中状态的View
 */

interface ViewHolderDecorate {

    fun decorate(viewHolder: RecyclerView.ViewHolder, adapter: MultipleAdapter): MultipleViewHolder;

}
