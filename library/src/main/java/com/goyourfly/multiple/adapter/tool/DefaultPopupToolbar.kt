package com.goyourfly.multiple.adapter.tool

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.view.View
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/8.
 */
class DefaultPopupToolbar(activity: Activity): PopupToolView(activity){
    var toolbar = Toolbar(activity)

    fun setTitle(title:String){
        toolbar.title = title
    }

    override fun numChanged(num:Int){
        setTitle("Select num:${num}")
    }

    override fun getToolView(): View {
        toolbar = Toolbar(activity)
        toolbar.setBackgroundColor(0xFF3F51B5.toInt())
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.navigationIcon = activity.resources.getDrawable(R.drawable.ic_arrow_back)
        toolbar.inflateMenu(R.menu.menu_multiple_select)
        toolbar.setNavigationOnClickListener {
            dismiss()
            callbackCancle?.cancelClick()
        }
        toolbar.setOnMenuItemClickListener({
            when(it.itemId){
                R.id.action_done ->{
                    dismiss()
                    callbackDone?.doneClick()
                }
            }
            true
        })
        return toolbar
    }

}
