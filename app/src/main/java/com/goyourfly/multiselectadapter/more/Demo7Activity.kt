package com.goyourfly.multiselectadapter.more

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.viewholder.view.CheckBoxFactory
import com.goyourfly.multiple.adapter.menu.SimpleDeleteMenuBar
import com.goyourfly.multiselectadapter.DemoSectionAdapter
import com.goyourfly.multiselectadapter.R

class Demo7Activity : com.goyourfly.multiselectadapter.RecyclerActivity() {
    val stateChangeListener = com.goyourfly.multiple.adapter.SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        val list = arrayListOf<String>()
        for (i in 0..100){
            list.add("index of $i")
        }
        val adapter = MyQuickAdapter(R.layout.item_section,list)
        recycler.adapter = MultipleSelect.with(this)
                .adapter(adapter)
                .decorateFactory(CheckBoxFactory(Gravity.RIGHT))
                .customMenu(SimpleDeleteMenuBar(this, resources.getColor(R.color.colorAccent), Gravity.BOTTOM))
                .linkList(list)
                .ignoreViewType(arrayOf(1))
                .stateChangeListener(stateChangeListener)
                .build()
    }

    class MyQuickAdapter(layout:Int,list:List<String>) : BaseQuickAdapter<String, BaseViewHolder>(layout,list) {
        override fun convert(baseViewHolder: BaseViewHolder, value: String) {
            baseViewHolder.getView<TextView>(R.id.section).text = value
             baseViewHolder.getView<TextView>(R.id.section).setOnClickListener {
                 Toast.makeText(it.context,"I am click:${baseViewHolder.adapterPosition}",Toast.LENGTH_LONG).show()
             }
        }
    }
}
