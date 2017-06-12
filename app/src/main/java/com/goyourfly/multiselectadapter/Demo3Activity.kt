package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.util.Log
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.binder.color.ColorFactory
import com.goyourfly.multiple.adapter.menu.SimpleDeleteMenuBar
import com.goyourfly.multiple.adapter.menu.SimpleDeleteSelectAllMenuBar

class Demo3Activity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoAdapter())
                .decorateFactory(ColorFactory())
                .customMenu(SimpleDeleteSelectAllMenuBar(this,resources.getColor(R.color.colorPrimary)))
                .build()

    }

    fun String.log(){
        Log.d("Demo3Activity",this)
    }
}
