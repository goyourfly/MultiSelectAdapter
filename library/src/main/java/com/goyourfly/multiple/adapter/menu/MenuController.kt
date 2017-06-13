package com.goyourfly.multiple.adapter.menu

/**
 * Created by gaoyufei on 2017/6/9.
 */

interface MenuController {

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
     * 全选
     */
    fun selectAll()

    /**
     * 全部不选
     */
    fun selectNothing()

    /**
     * Confirm and exit select mode
     */
    fun done(refresh: Boolean = true)

    /**
     * Delete and exit select mode
     */
    fun delete(refresh: Boolean = true)

    /**
     * Cancel and exit select mode
     */
    fun cancel(refresh: Boolean = true):Boolean

}
