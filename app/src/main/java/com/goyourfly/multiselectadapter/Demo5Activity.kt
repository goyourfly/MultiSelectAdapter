package com.goyourfly.multiselectadapter

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.binder.color.ColorFactory
import com.goyourfly.multiple.adapter.menu.CustomMenuBar

class Demo5Activity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(DemoAdapter())
                .decorateFactory(ColorFactory())
                .customMenu(MyMenuBar(this, R.menu.menu_select, resources.getColor(R.color.colorPrimary)))
                .build()

    }

    class MyMenuBar(activity:Activity,menuId:Int,color:Int)
        :CustomMenuBar(activity,menuId,color,Gravity.TOP){

        override fun onUpdateTitle(selectCount: Int, total: Int) {
            toolbar.title = "选中：$selectCount,总共：$total"
        }

        override fun onMenuItemClick(menuItem: MenuItem) {
            Log.d("Demo5Activity","itemId:${menuItem.title}")
        }
    }
}
