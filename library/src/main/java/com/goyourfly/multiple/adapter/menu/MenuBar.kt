package com.goyourfly.multiple.adapter.menu

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

/**
 * Created by gaoyufei on 2017/6/8.
 */

abstract class MenuBar(val activity: Activity){
    var popupWindow: PopupWindow? = null
    var controler: MenuControl? = null

    fun initControl(menuControl: MenuControl){
        this.controler = menuControl
    }

    abstract fun getContentView(): View;

    abstract fun numChanged(num:Int)

    fun show(){
        popupWindow = PopupWindow(getContentView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow?.showAtLocation(activity.window.decorView, Gravity.TOP,0,0)
    }

    fun dismiss(){
        popupWindow?.dismiss()
    }
}
