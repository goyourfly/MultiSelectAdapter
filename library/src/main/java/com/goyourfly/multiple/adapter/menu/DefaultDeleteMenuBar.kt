package com.goyourfly.multiple.adapter.menu

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.view.View
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/8.
 */
class DefaultDeleteMenuBar(activity: Activity, val color:Int): MenuBar(activity){
    var toolbar = Toolbar(activity)

    fun setTitle(title:String){
        toolbar.title = title
    }

    override fun numChanged(num:Int){
        setTitle("Select num:${num}")
    }

    override fun getContentView(): View {
        toolbar = Toolbar(activity)
        toolbar.setBackgroundColor(color)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.navigationIcon = activity.resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.inflateMenu(R.menu.menu_multiple_select_2)
        toolbar.setNavigationOnClickListener {
            dismiss()
            controler?.cancel()
        }
        toolbar.setOnMenuItemClickListener({
            when(it.itemId){
                R.id.action_done ->{
                    dismiss()
                    controler?.done()
                }
                R.id.action_delete ->{
                    dismiss()
                    controler?.done()
                }
            }
            true
        })
        return toolbar
    }

}
