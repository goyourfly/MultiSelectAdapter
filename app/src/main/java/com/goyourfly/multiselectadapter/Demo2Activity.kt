package com.goyourfly.multiselectadapter

import android.os.Bundle
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.binder.view.CheckBoxFactory

class Demo2Activity : RecyclerActivity() {
    val stateChangeListener = SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoSectionAdapter())
                .decorateFactory(CheckBoxFactory())
                .ignore(arrayOf(1))
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
