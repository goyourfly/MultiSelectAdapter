package com.goyourfly.multiselectadapter

import android.graphics.Color
import android.os.Bundle
import com.goyourfly.multiple.adapter.*
import com.goyourfly.multiple.adapter.holder.RadioBtnDecorateFactory

class Demo1Activity : RecyclerActivity() {
    val stateChangeListener = SimpleStateChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoAdapter())
                .decorateFactory(RadioBtnDecorateFactory(colorFilter = Color.RED))
                .stateChangeListener(stateChangeListener)
                .build()
    }
}
