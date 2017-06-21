package com.goyourfly.multiselectadapter.more

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.viewholder.color.ColorFactory
import com.goyourfly.multiple.adapter.menu.CustomMenuBar
import com.goyourfly.multiple.adapter.menu.MenuController
import com.goyourfly.multiselectadapter.DemoAdapter
import com.goyourfly.multiselectadapter.R
import com.goyourfly.multiselectadapter.RecyclerActivity

class Demo5Activity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect.with(this)
                .adapter(DemoAdapter())
                .decorateFactory(ColorFactory())
                .customMenu(MyMenuBar(this, R.menu.menu_select, resources.getColor(R.color.colorPrimary)))
                .build()

    }

    class MyMenuBar(activity: Activity, menuId:Int, color:Int)
        : CustomMenuBar(activity,menuId,color, android.view.Gravity.TOP){
        override fun onMenuItemClick(menuItem: MenuItem, controller: MenuController) {
            android.util.Log.d("Demo5Activity","itemId:${menuItem.title}")
        }

        override fun onUpdateTitle(selectCount: Int, total: Int) {
            toolbar.title = "选中：$selectCount,总共：$total"
        }
    }
}
