package com.goyourfly.multiple.adapter.tool

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

/**
 * Created by gaoyufei on 2017/6/8.
 */

abstract class PopupToolView(val activity: Activity){
    interface CallbackCancel{
        fun cancelClick()
    }

    interface CallbackDone{
        fun doneClick()
    }

    var callbackDone:CallbackDone? = null
    var callbackCancle:CallbackCancel? = null

    var popupWindow: PopupWindow? = null

    abstract fun getToolView(): View;

    abstract fun numChanged(num:Int)

    fun setDoneClickListener(callbackDone: CallbackDone){
        this.callbackDone = callbackDone
    }

    fun setCancelClickListener(callbackCancel: CallbackCancel){
        this.callbackCancle = callbackCancle
    }

    fun show(){
        popupWindow = PopupWindow(getToolView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow?.showAtLocation(activity.window.decorView, Gravity.TOP,0,0)
    }

    fun dismiss(){
        popupWindow?.dismiss()
    }
}
