package com.goyourfly.multiple.adapter.viewholder.view

/**
 * Created by gaoyufei on 2017/6/8.
 */
class CustomViewHolder(view: android.view.View,
                       viewHolder: android.support.v7.widget.RecyclerView.ViewHolder,
                       adapter: com.goyourfly.multiple.adapter.MultipleAdapter,
                       val animationInterface: com.goyourfly.multiple.adapter.viewholder.AnimationInterface,
                       val selectViewContainer: android.view.View,
                       val selectView: android.view.View,
                       val unSelectView: android.view.View) : com.goyourfly.multiple.adapter.viewholder.BaseViewHolder(view, viewHolder, adapter) {
    init {
        selectView.setOnTouchListener(onTouchListener)
        unSelectView.setOnTouchListener(onTouchListener)
    }


    override fun selectStateChanged(state: Int) {
        if(state == com.goyourfly.multiple.adapter.SelectState.UN_SELECT){
            selectView.visibility = android.view.View.INVISIBLE
            unSelectView.visibility = android.view.View.VISIBLE
        }else if(state == com.goyourfly.multiple.adapter.SelectState.SELECT){
            selectView.visibility = android.view.View.VISIBLE
            unSelectView.visibility = android.view.View.INVISIBLE
        }
    }

    override fun showStateChanged(toState: Int) {
        when(toState){
            com.goyourfly.multiple.adapter.ViewState.DEFAULT -> {
                selectViewContainer.visibility = android.view.View.GONE
            }
            com.goyourfly.multiple.adapter.ViewState.DEFAULT_TO_SELECT -> {
                animationInterface.onShowAnimation(itemView,selectViewContainer)
            }
            com.goyourfly.multiple.adapter.ViewState.SELECT -> {
                selectViewContainer.visibility = android.view.View.VISIBLE
            }
            com.goyourfly.multiple.adapter.ViewState.SELECT_TO_DEFAULT -> {
                animationInterface.onHideAnimation(itemView,selectViewContainer)
            }
        }
    }


    fun Float.toPx(context: android.content.Context): Int {
        return android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_DIP, this, context.resources.getDisplayMetrics()).toInt();
    }
}
