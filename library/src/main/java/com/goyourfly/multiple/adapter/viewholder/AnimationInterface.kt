package com.goyourfly.multiple.adapter.viewholder

import android.view.View

/**
 * Created by gaoyufei on 2017/6/11.
 */

interface AnimationInterface{
    /**
     * 显示时候的动画
     */
    fun onShowAnimation(itemView:View,selectView:View)

    /**
     * 隐藏时的动画
     */
    fun onHideAnimation(itemView: View,selectView: View)
}