package com.goyourfly.multiple.adapter.tool

/**
 * Created by gaoyufei on 2017/6/9.
 */

interface ModeControl {

    /**
     * 刷新列表
     */
    fun refresh()
    /**
     * Enter select mode
     */
    fun selectMode(refresh:Boolean = true)

    /**
     * 获取当前选中
     */
    fun getSelect():ArrayList<Int>

    fun getTotal():Int

    /**
     * Confirm and exit select mode
     */
    fun done(refresh: Boolean = true)

    /**
     * Cancel and exit select mode
     */
    fun cancel(refresh: Boolean = true):Boolean

}
