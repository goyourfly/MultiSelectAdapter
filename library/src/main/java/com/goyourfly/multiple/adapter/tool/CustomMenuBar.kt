package com.goyourfly.multiple.adapter.tool

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.view.View
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/8.
 */
class CustomMenuBar(activity: Activity,
                    val menuId:Int,
                    val menuBgColor:Int = Color.GRAY,
                    val listener: Toolbar.OnMenuItemClickListener): MenuBar(activity){
    var toolbar = Toolbar(activity)

    fun setTitle(title:String){
        toolbar.title = title
    }

    override fun numChanged(num:Int){
        setTitle("${num}/${controler?.getTotal()}")
    }

    override fun getContentView(): View {
        toolbar = Toolbar(activity)
        toolbar.setBackgroundColor(menuBgColor)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.inflateMenu(menuId)
        toolbar.navigationIcon = activity.resources.getDrawable(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            dismiss()
            controler?.cancel()
        }
        toolbar.setOnMenuItemClickListener(listener)
        return toolbar
    }

}
