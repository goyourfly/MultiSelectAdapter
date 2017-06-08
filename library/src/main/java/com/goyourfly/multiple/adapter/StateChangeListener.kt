package com.goyourfly.multiple.adapter

import android.util.SparseBooleanArray

/**
 * Created by gaoyufei on 2017/6/8.
 */

interface StateChangeListener{

    fun onSelectMode()

    fun onSelect(position:Int,selectNum:Int)

    fun onUnSelect(position: Int,selectNum: Int)

    fun onDone(array: ArrayList<Int>)

    fun onCancel()
}
