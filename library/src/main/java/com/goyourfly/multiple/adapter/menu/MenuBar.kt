package com.goyourfly.multiple.adapter.menu

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

/**
 * Created by gaoyufei on 2017/6/8.
 */

abstract class MenuBar(val activity: Activity,val gravity: Int){
    var popupWindow: PopupWindow? = null
    var controler: MenuController? = null

    fun initControl(menuController: MenuController){
        this.controler = menuController
    }

    abstract fun getContentView(): View;

    abstract fun onUpdateTitle(selectCount:Int,total:Int)

    fun show(){
        popupWindow = PopupWindow(getContentView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow?.showAtLocation(activity.window.decorView, gravity,0,0)
    }

    fun dismiss(){
        popupWindow?.dismiss()
    }
}
