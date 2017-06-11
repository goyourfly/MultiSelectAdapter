package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.util.Log
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.binder.color.ColorFactory

class Demo2Activity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoAdapter())
                .decorateFactory(ColorFactory())
                .build()

    }

    fun String.log(){
        Log.d("Demo2Activity",this)
    }
}
