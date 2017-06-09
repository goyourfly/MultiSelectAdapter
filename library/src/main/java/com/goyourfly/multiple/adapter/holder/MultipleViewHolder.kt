package com.goyourfly.multiple.adapter.holder

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.ShowState

/**
 * Created by gaoyufei on 2017/6/8.
 */
abstract class MultipleViewHolder(val view: View,
                                  val viewHolder: RecyclerView.ViewHolder,
                                  val adapter: MultipleAdapter) : RecyclerView.ViewHolder(view) {

    init {
        viewHolder.itemView.setOnTouchListener(OnTouchListener(adapter, this))
    }

    abstract fun selectStateChanged(state: Int)

    open fun showStateChanged(toState: Int) {}

    class OnTouchListener(val adapter: MultipleAdapter, val holder: RecyclerView.ViewHolder) : View.OnTouchListener {
        var startX = 0F
        var startY = 0F
        var moveX = 0F
        var moveY = 0F
        var startTime = 0L
        val CLICK_ACTION_THRESHHOLD = 20
        val CLICK_LONG_TIME = 500L
        var isTouching = false
        val handler = Handler()
        val run = Runnable {
            if(isTouching && isAClick(startX,moveX,startY,moveY)){
                adapter.onItemLongClick(holder.adapterPosition)
            }
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            Log.d("onTouch","event:" + event.action)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isTouching = true
                    startX = event.x
                    startY = event.y
                    startTime = System.currentTimeMillis()
                    handler.postDelayed(run,CLICK_LONG_TIME)
                }

                MotionEvent.ACTION_MOVE ->{
                    moveX = event.x
                    moveY = event.y
                }

                MotionEvent.ACTION_UP -> {
                    val endX = event.x
                    val endY = event.y
                    if (isTouching && isAClick(startX, endX, startY, endY)) {
                        if (adapter.showState == ShowState.SELECT) {
                            adapter.onItemClick(holder.adapterPosition)
                        } else {
                            v.performClick()
                        }
                    }
                    isTouching = false
                    handler.removeCallbacks(run)
                }

                MotionEvent.ACTION_CANCEL -> {
                    isTouching = false
                    handler.removeCallbacks(run)
                }
            }
            return true
        }

        private fun isAClick(startX: Float, endX: Float, startY: Float, endY: Float): Boolean {
            val differenceX = Math.abs(startX - endX)
            val differenceY = Math.abs(startY - endY)
            if (differenceX > CLICK_ACTION_THRESHHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHHOLD) {
                return false
            }
            return true
        }

        private fun isLongClick(starTime:Long,endTime: Long):Boolean{
            if(endTime - starTime > CLICK_LONG_TIME){
                return true
            }
            return false
        }
    }
}
