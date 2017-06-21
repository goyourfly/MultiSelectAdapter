package com.goyourfly.multiple.adapter.viewholder.view

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/11.
 */

class RadioBtnFactory(val color: Int,
                      val duration:Int,
                      val gravity:Int) : CustomViewFactory() {
    constructor() : this(Color.RED, 300, Gravity.LEFT)
    constructor(gravity: Int) : this(Color.RED, 300, gravity)
    constructor(color:Int,gravity: Int) : this(color, 300, gravity)

    override fun onShowAnimation(itemView: View, selectView: View) {
        selectView.visibility = View.VISIBLE
//        if(gravity == Gravity.LEFT
//                || gravity == Gravity.START) {
//            itemView.translationX = -selectView.measuredWidth.toFloat()
//            itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
//        }else if(gravity == Gravity.RIGHT||gravity == Gravity.END){
//            itemView.translationX = selectView.measuredWidth.toFloat()
//            itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
//        }
    }

    override fun onHideAnimation(itemView: View, selectView: View) {
        selectView.visibility = View.GONE
//        if(gravity == Gravity.LEFT
//                || gravity == Gravity.START) {
//            itemView.translationX = selectView.measuredWidth.toFloat()
//            itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
//        }else if(gravity == Gravity.RIGHT||gravity == Gravity.END){
//            itemView.translationX = -selectView.measuredWidth.toFloat()
//            itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
//        }
    }


    override fun onCreateRootView(context: Context): ViewGroup {
        val root = RelativeLayout(context)
        return root
    }

    override fun onBindSelectView(root: ViewGroup, itemView: View, selectView: View) {
        root.removeAllViews()
        if (gravity == Gravity.LEFT
                || gravity == Gravity.START) {
            val params =
                    RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.MATCH_PARENT)
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            params.addRule(RelativeLayout.CENTER_VERTICAL)
            root.addView(selectView, params)
            val paramsItemView = RelativeLayout.LayoutParams(itemView.layoutParams.width,
                    itemView.layoutParams.height)
            paramsItemView.addRule(RelativeLayout.RIGHT_OF, selectView.id)
            itemView.layoutParams = paramsItemView
            root.addView(itemView)
        } else if (gravity == Gravity.RIGHT
                || gravity == Gravity.END) {
            val params =
                    RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.MATCH_PARENT)
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            params.addRule(RelativeLayout.CENTER_VERTICAL)
            root.addView(selectView, params)
            val paramsItemView = RelativeLayout.LayoutParams(itemView.layoutParams.width,
                    itemView.layoutParams.height)
            paramsItemView.addRule(RelativeLayout.LEFT_OF, selectView.id)
            root.addView(itemView, paramsItemView)
        } else {
            throw IllegalArgumentException("Gravity only support LEFT or RIGHT")
        }
    }

    override fun onCreateSelectView(context: Context): View {
        val layout = FrameLayout(context)
        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.ic_radio_button_checked_black_24dp)
        imageView.setColorFilter(color)
        val params = FrameLayout.LayoutParams(48F.toPx(context), FrameLayout.LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER
        layout.addView(imageView, params)
        return layout
    }

    override fun onCreateNormalView(context: Context): View {
        val layout = FrameLayout(context)
        val imageView = ImageView(context)
        imageView.setColorFilter(color)
        imageView.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp)
        val params = FrameLayout.LayoutParams(48F.toPx(context), FrameLayout.LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER
        layout.addView(imageView, params)
        return layout
    }
}