package com.goyourfly.multiple.adapter.viewholder

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.ViewState

/**
 * Created by gaoyufei on 2017/7/20.
 */

internal class InterceptFrameLayout(context: Context, val adapter: MultipleAdapter, child: View) : FrameLayout(context) {
    val CLICK_ACTION_THRESHHOLD = 20
    val CLICK_LONG_TIME = 500L

    var startX = 0F
    var startY = 0F
    var moveX = 0F
    var moveY = 0F
    var downTime = 0L
    var isTouching = false
    var isLongClick = false
    var touchDownTime = 0L

    private var onLongClicked: (() -> Unit)? = null
    private var onClick: (() -> Unit)? = null

    val run = Runnable {
        if (isTouching && !isLongClick && isAClick()) {
            // 制作一个假的事件
            val event = MotionEvent.obtain(
                    downTime,
                    System.currentTimeMillis(),
                    MotionEvent.ACTION_MOVE,
                    moveX,
                    moveY,
                    0)
            isLongClick = true
            onTouchEvent(event)
        }
    }

    init {
        val childParams = child.layoutParams
        val params =
                if (childParams != null)
                    LayoutParams(childParams)
                else
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams = params
        addView(child)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.d("...", "onIntercept:$ev")
        val action = MotionEventCompat.getActionMasked(ev)
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isLongClick = false
                isTouching = true
                startX = ev.x
                startY = ev.y
                moveX = ev.x
                moveY = ev.y
                downTime = System.currentTimeMillis()
                handler.postDelayed(run, CLICK_LONG_TIME)
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = ev.x
                moveY = ev.y
                if (isInterceptLong() && isAClick()) {
                    isLongClick = true
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isTouching = false
                handler.removeCallbacks(run)
            }
        }
        return isLongClick || adapter.showState == ViewState.SELECT
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(ev)
        Log.d("...", "onTouch:$ev")
        if (isTouching
                && isLongClick
                && action != MotionEvent.ACTION_CANCEL) {
            handler.removeCallbacks(run)
            handler.post {
                onLongClicked?.invoke()
            }
            return true
        }
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isTouching = true
                startX = ev.x
                startY = ev.y
                moveX = ev.x
                moveY = ev.y
                touchDownTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = ev.x
                moveY = ev.y
            }
            MotionEvent.ACTION_UP -> {
                isTouching = false
                handler.removeCallbacks(run)
                if (adapter.showState == ViewState.SELECT
                        && !isTouchLong()
                        && isAClick()) {
                    onClick?.invoke()
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                isTouching = false
                handler.removeCallbacks(run)
            }
        }
        return true
    }


    fun setLongClickCallback(callback: () -> Unit): Unit {
        onLongClicked = callback
    }

    fun setSelectStateClickCallback(callback: () -> Unit): Unit {
        onClick = callback
    }

    private fun isInterceptLong() = System.currentTimeMillis() - downTime > CLICK_LONG_TIME
    private fun isTouchLong() = System.currentTimeMillis() - touchDownTime > CLICK_LONG_TIME

    private fun isAClick(): Boolean {
        val differenceX = Math.abs(startX - moveX)
        val differenceY = Math.abs(startY - moveY)
        if (differenceX > CLICK_ACTION_THRESHHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHHOLD) {
            return false
        }
        return true
    }
}