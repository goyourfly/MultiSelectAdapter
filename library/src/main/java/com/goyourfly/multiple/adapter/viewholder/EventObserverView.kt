package com.goyourfly.multiple.adapter.viewholder

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.ViewState

/**
 * Created by gaoyufei on 2017/7/20.
 * 用于检测和拦截长按手势的ViewGroup
 */

internal class EventObserverView(context: Context, val adapter: MultipleAdapter, child: View) : FrameLayout(context) {
    val CLICK_ACTION_THRESHHOLD = ViewConfiguration.get(context).getScaledTouchSlop()
    val CLICK_LONG_TIME = ViewConfiguration.getLongPressTimeout().toLong()

    var startX = 0F
    var startY = 0F
    var moveX = 0F
    var moveY = 0F
    var downTime = 0L
    var isTouching = false
    var mHasPerformedLongPress = false;

    private var onLongClicked: (() -> Unit)? = null
    private var onClick: (() -> Unit)? = null

    val run = Runnable {
        if (isTouching && !mHasPerformedLongPress && isAClick()) {
            onLongClick()
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
        val action = MotionEventCompat.getActionMasked(ev)
        var isLongClick = false
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mHasPerformedLongPress = false
                isTouching = true
                startX = ev.x
                startY = ev.y
                moveX = ev.x
                moveY = ev.y
                downTime = System.currentTimeMillis()
                postDelayed(run, CLICK_LONG_TIME)
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
                removeCallbacks(run)
            }
        }
        return isLongClick || adapter.showState == ViewState.SELECT
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    fun onLongClick() {
        removeCallbacks(run)
        post {
            mHasPerformedLongPress = true
            onLongClicked?.invoke()
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(ev)
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isTouching = true
                mHasPerformedLongPress = false
                startX = ev.x
                startY = ev.y
                moveX = ev.x
                moveY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = ev.x
                moveY = ev.y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isTouching = false
                removeCallbacks(run)
                if (action == MotionEvent.ACTION_UP
                        && adapter.showState == ViewState.SELECT
                        && isAClick()) {
                    onClick?.invoke()
                }
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

    private fun isAClick(): Boolean {
        val differenceX = Math.abs(startX - moveX)
        val differenceY = Math.abs(startY - moveY)
        if (differenceX > CLICK_ACTION_THRESHHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHHOLD) {
            return false
        }
        return true
    }
}