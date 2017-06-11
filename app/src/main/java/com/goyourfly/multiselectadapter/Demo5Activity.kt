package com.goyourfly.multiselectadapter

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.binder.color.ColorFactory
import com.goyourfly.multiple.adapter.menu.CustomMenuBar

class Demo5Activity : RecyclerActivity() {
    val menuClickListener = Toolbar.OnMenuItemClickListener({
        it -> "itemId:${it.title}".log()
        true
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoAdapter())
                .decorateFactory(ColorFactory())
                .customControl(CustomMenuBar(this,R.menu.menu_select, Color.BLACK,menuClickListener))
                .build()

    }

    fun String.log(){
        Log.d("Demo3Activity",this)
    }
}
