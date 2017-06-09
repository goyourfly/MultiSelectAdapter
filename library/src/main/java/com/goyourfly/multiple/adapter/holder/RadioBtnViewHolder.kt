package com.goyourfly.multiple.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.goyourfly.multiple.adapter.Gravity
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.SelectState
import com.goyourfly.multiple.adapter.ShowState

/**
 * Created by gaoyufei on 2017/6/8.
 */
class RadioBtnViewHolder(view: View,
                         viewHolder: RecyclerView.ViewHolder,
                         adapter: MultipleAdapter,
                         val selectViewContainer: View,
                         val selectView: View,
                         val unSelectView: View,
                         val gravity:Int,
                         val duration:Long) : BaseViewHolder(view, viewHolder, adapter) {


    override fun selectStateChanged(state: Int) {
        if(state == SelectState.UN_SELECT){
            selectView.visibility = View.INVISIBLE
            unSelectView.visibility = View.VISIBLE
        }else if(state == SelectState.SELECT){
            selectView.visibility = View.VISIBLE
            unSelectView.visibility = View.INVISIBLE
        }
    }

    override fun showStateChanged(toState: Int) {
        when(toState){
            ShowState.DEFAULT -> {
                selectViewContainer.visibility = View.GONE
            }
            ShowState.DEFAULT_TO_SELECT -> {
                selectViewContainer.visibility = View.VISIBLE
                if(gravity == Gravity.LEFT) {
                    itemView.translationX = -selectViewContainer.measuredWidth.toFloat()
                    itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
                }else if(gravity == Gravity.RIGHT){
//                    itemView.translationX = selectViewContainer.measuredWidth.toFloat()
                    itemView.animate().translationX(-selectViewContainer.measuredWidth.toFloat()).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
                }
            }
            ShowState.SELECT -> {
                selectViewContainer.visibility = View.VISIBLE
            }
            ShowState.SELECT_TO_DEFAULT -> {
                selectViewContainer.visibility = View.GONE
                if(gravity == Gravity.LEFT){
                    itemView.translationX = selectViewContainer.measuredWidth.toFloat()
                    itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
                }else if(gravity == Gravity.RIGHT){
//                    itemView.translationX = -selectViewContainer.measuredWidth.toFloat()
                    itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
                }
            }
        }
    }


    fun Float.toPx(context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.getDisplayMetrics()).toInt();
    }
}
