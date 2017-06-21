package com.goyourfly.multiple.adapter.menu

import android.app.Activity
import android.view.Gravity
import android.view.MenuItem
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/8.
 */
class SimpleDeleteMenuBar(activity: Activity, val color:Int, gravity:Int = Gravity.TOP):
        CustomMenuBar(activity,R.menu.menu_multiple_select_delete,color,gravity){
    override fun onMenuItemClick(menuItem: MenuItem,controller: MenuController) {
        when(menuItem.itemId){
            R.id.action_done ->{
                dismiss()
                controller.done()
            }
            R.id.action_delete ->{
                dismiss()
                controller.delete()
            }
        }
    }
}
