package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.view.Gravity
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.viewholder.view.RadioBtnFactory

class Demo1Activity : RecyclerActivity() {
    val stateChangeListener = SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoSectionAdapter())
                .decorateFactory(RadioBtnFactory(gravity = Gravity.RIGHT))
                .ignoreViewType(arrayOf(1))
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
