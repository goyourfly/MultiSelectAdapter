package com.goyourfly.multiple.adapter.binder.view

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.goyourfly.multiple.adapter.R

/**
 * Created by gaoyufei on 2017/6/11.
 */

class RadioBtnFactory(val color: Int = Color.RED, val duration:Long = 300L) : CustomViewFactory() {
    override fun onShowAnimation(itemView: View, selectView: View) {
        selectView.visibility = View.VISIBLE
        itemView.translationX = -selectView.measuredWidth.toFloat()
        itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
    }

    override fun onHideAnimation(itemView: View, selectView: View) {
        selectView.visibility = View.GONE
        itemView.translationX = selectView.measuredWidth.toFloat()
        itemView.animate().translationX(0F).setInterpolator(AccelerateDecelerateInterpolator()).setDuration(duration).start()
    }


    override fun onCreateRootView(context: Context): ViewGroup {
        return LinearLayout(context)
    }

    override fun onBindSelectView(root: ViewGroup, itemView: View, selectView: View) {
        root.removeAllViews()
        root.addView(selectView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        root.addView(itemView)
    }

    override fun onCreateSelectView(context: Context): View {
        val layout = FrameLayout(context)
        layout.setBackgroundColor(0xFFE0E0E0.toInt())
        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.ic_radio_button_checked_black_24dp)
        imageView.setColorFilter(color)
        val params = FrameLayout.LayoutParams(48F.toPx(context), FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        layout.addView(imageView, params)
        return layout
    }

    override fun onCreateNormalView(context: Context): View {
        val layout = FrameLayout(context)
        layout.setBackgroundColor(0xFFE0E0E0.toInt())
        val imageView = ImageView(context)
        imageView.setColorFilter(color)
        imageView.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp)
        val params = FrameLayout.LayoutParams(48F.toPx(context), FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        layout.addView(imageView, params)
        return layout
    }
}