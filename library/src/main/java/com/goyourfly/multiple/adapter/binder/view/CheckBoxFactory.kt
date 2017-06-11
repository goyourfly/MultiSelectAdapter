package com.goyourfly.multiple.adapter.binder.view

import android.widget.FrameLayout

/**
 * Created by gaoyufei on 2017/6/11.
 */

class CheckBoxFactory(val color: Int = android.graphics.Color.RED, val duration: Int = 300) : CustomViewFactory() {
    override fun onShowAnimation(itemView: android.view.View, selectView: android.view.View) {
        selectView.visibility = android.view.View.VISIBLE
    }

    override fun onHideAnimation(itemView: android.view.View, selectView: android.view.View) {
        selectView.visibility = android.view.View.GONE
    }

    override fun onCreateSelectView(context: android.content.Context): android.view.View {
        val imageView = android.widget.ImageView(context)
        imageView.setImageResource(com.goyourfly.multiple.adapter.R.drawable.ic_check_box_black_24dp)
        imageView.setColorFilter(color)
        return imageView
    }

    override fun onCreateNormalView(context: android.content.Context): android.view.View {
        val imageView = android.widget.ImageView(context)
        imageView.setColorFilter(color)
        imageView.setImageResource(com.goyourfly.multiple.adapter.R.drawable.ic_check_box_outline_blank_black_24dp)
        return imageView
    }

    override fun onCreateRootView(context: android.content.Context): android.view.ViewGroup {
        return android.widget.FrameLayout(context)
    }

    override fun onBindSelectView(root: android.view.ViewGroup, itemView: android.view.View, selectView: android.view.View) {
        root.removeAllViews()
        root.addView(itemView)
        val params = android.widget.FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = android.view.Gravity.RIGHT
        params.topMargin = 8.toPx(root.context)
        params.rightMargin = 8.toPx(root.context)
        root.addView(selectView, params)
    }

}