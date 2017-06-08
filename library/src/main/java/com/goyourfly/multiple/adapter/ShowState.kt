package com.goyourfly.multiple.adapter

/**
 * Created by gaoyufei on 2017/6/8.
 */
object ShowState {
    val DEFAULT = 1
    val DEFAULT_TO_SELECT = 2
    val SELECT = 3
    val SELECT_TO_DEFAULT = 4

    fun getNextState(fromState: Int): Int {
        var toState = fromState + 1
        if (toState > SELECT_TO_DEFAULT)
            toState = DEFAULT
        return toState
    }
}
