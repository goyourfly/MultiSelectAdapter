package com.goyourfly.multiple.adapter.menu

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/8.
 */
abstract class CustomMenuBar(activity: Activity,
                             val menuId: Int,
                             val menuBgColor: Int = Color.GRAY,
                             gravity:Int) : MenuBar(activity,gravity) {
    var toolbar = Toolbar(activity)

    override fun onUpdateTitle(selectCount: Int, total: Int) {
        toolbar.title = "${selectCount}/${total}"
    }

    override fun getContentView(): View {
        toolbar = Toolbar(activity)
        toolbar.setBackgroundColor(menuBgColor)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.inflateMenu(menuId)
        toolbar.navigationIcon = activity.resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            dismiss()
            controler?.cancel()
        }
        toolbar.setOnMenuItemClickListener {
            onMenuItemClick(it,controler!!)
            true
        }
        return toolbar
    }

    abstract fun onMenuItemClick(menuItem: MenuItem,controller: MenuController)

}
