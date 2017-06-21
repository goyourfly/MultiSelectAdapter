package com.goyourfly.multiselectadapter.more

import android.os.Bundle
import android.view.Gravity
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.viewholder.view.CheckBoxFactory
import com.goyourfly.multiple.adapter.menu.SimpleDeleteMenuBar
import com.goyourfly.multiselectadapter.R

class Demo2Activity : com.goyourfly.multiselectadapter.RecyclerActivity() {
    val stateChangeListener = com.goyourfly.multiple.adapter.SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = com.goyourfly.multiselectadapter.DemoSectionAdapter()
        recycler.adapter = com.goyourfly.multiple.adapter.MultipleSelect.with(this)
                .adapter(adapter)
                .decorateFactory(CheckBoxFactory(Gravity.RIGHT))
                .customMenu(com.goyourfly.multiple.adapter.menu.SimpleDeleteMenuBar(this, resources.getColor(R.color.colorAccent), Gravity.BOTTOM))
                .linkList(adapter.list)
                .ignoreViewType(arrayOf(1))
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
