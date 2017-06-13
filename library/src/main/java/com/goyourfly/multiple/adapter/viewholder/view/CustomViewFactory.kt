package com.goyourfly.multiple.adapter.viewholder.view

import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.goyourfly.multiple.adapter.R
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder
import viewholder.view.CustomViewHolder

/**
 * Created by gaoyufei on 2017/6/8.
 * 继承这个类的类都是自定义选择和未选中view的
 */
abstract class CustomViewFactory : com.goyourfly.multiple.adapter.viewholder.DecorateFactory, com.goyourfly.multiple.adapter.viewholder.AnimationInterface {

    override fun decorate(viewHolder: android.support.v7.widget.RecyclerView.ViewHolder, adapter: com.goyourfly.multiple.adapter.MultipleAdapter): com.goyourfly.multiple.adapter.viewholder.BaseViewHolder {
        val context = viewHolder.itemView.context
        val root = onCreateRootView(context)
        val rootParams = ViewGroup.LayoutParams(viewHolder.itemView.layoutParams)
        root.layoutParams = rootParams
        return createViewHolder(context, root, viewHolder, adapter)
    }


    fun createViewHolder(context: android.content.Context,
                         root: android.view.ViewGroup,
                         viewHolder: android.support.v7.widget.RecyclerView.ViewHolder,
                         adapter: com.goyourfly.multiple.adapter.MultipleAdapter): BaseViewHolder {
        val selectView = onCreateSelectView(context)
        val defaultView = onCreateNormalView(context)
        val selectRoot = FrameLayout(context)
        selectRoot.id = R.id.id_select_view
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER
        selectRoot.addView(defaultView, layoutParams)
        selectRoot.addView(selectView, layoutParams)
        selectRoot.visibility = android.view.View.GONE

        onBindSelectView(root, viewHolder.itemView, selectRoot)

        selectRoot.measure(root.width, root.height)
        selectRoot.visibility = android.view.View.GONE
        return CustomViewHolder(root, viewHolder, adapter, this, selectRoot, selectView, defaultView)
    }

    /**
     * 生成默认的SelectView
     */
    abstract fun onCreateSelectView(context: android.content.Context): android.view.View

    /**
     * 生成默认的UnSelectView
     */
    abstract fun onCreateNormalView(context: android.content.Context): android.view.View


    /**
     * 创建最外层的View
     */
    abstract fun onCreateRootView(context: android.content.Context): android.view.ViewGroup


    /**
     * 绑定SelectView
     */
    abstract fun onBindSelectView(root: android.view.ViewGroup, itemView: android.view.View, selectView: android.view.View)


    fun Float.toPx(context: android.content.Context): Int {
        return android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_DIP, this, context.resources.getDisplayMetrics()).toInt();
    }

    fun Int.toPx(context: android.content.Context): Int {
        return android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.getDisplayMetrics()).toInt();
    }
}
