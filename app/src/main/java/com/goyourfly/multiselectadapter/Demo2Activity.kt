package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.view.Gravity
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.binder.view.CheckBoxFactory
import com.goyourfly.multiple.adapter.menu.SimpleDeleteMenuBar

class Demo2Activity : RecyclerActivity() {
    val stateChangeListener = SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoSectionAdapter())
                .decorateFactory(CheckBoxFactory(gravity = Gravity.LEFT))
                .customMenu(SimpleDeleteMenuBar(this,resources.getColor(R.color.colorAccent),Gravity.BOTTOM))
                .ignore(arrayOf(1))
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
