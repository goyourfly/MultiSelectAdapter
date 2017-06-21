package com.goyourfly.multiselectadapter.more

import android.os.Bundle
import android.view.Gravity
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.SimpleStateChangeListener
import com.goyourfly.multiple.adapter.viewholder.view.RadioBtnFactory
import com.goyourfly.multiselectadapter.DemoSectionAdapter
import com.goyourfly.multiselectadapter.RecyclerActivity

class Demo1Activity : RecyclerActivity() {
    val stateChangeListener = SimpleStateChangeListener()
    val demoAdapter = DemoSectionAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect.with(this)
                .adapter(demoAdapter)
                .decorateFactory(RadioBtnFactory(Gravity.RIGHT))
                .ignoreViewType(arrayOf(1))
                .linkList(demoAdapter.list)
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
