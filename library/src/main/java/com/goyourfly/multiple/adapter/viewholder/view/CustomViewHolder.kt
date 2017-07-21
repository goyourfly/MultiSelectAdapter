package viewholder.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.*
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.SelectState
import com.goyourfly.multiple.adapter.ViewState
import com.goyourfly.multiple.adapter.viewholder.AnimationInterface
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder

/**
 * Created by gaoyufei on 2017/6/8.
 */
class CustomViewHolder(root: View,
                       viewHolder: RecyclerView.ViewHolder,
                       adapter: MultipleAdapter,
                       val animationInterface: AnimationInterface,
                       val selectViewContainer: View,
                       val selectView: View,
                       val unSelectView: View) : BaseViewHolder(root, viewHolder, adapter) {
//    init {
//        selectView.setOnTouchListener(onTouchListener)
//        unSelectView.setOnTouchListener(onTouchListener)
//    }


    override fun selectStateChanged(state: Int) {
        if(state == SelectState.UN_SELECT){
            selectView.visibility = INVISIBLE
            unSelectView.visibility = VISIBLE
        }else if(state == SelectState.SELECT){
            selectView.visibility = VISIBLE
            unSelectView.visibility = INVISIBLE
        }
    }

    override fun showStateChanged(toState: Int) {
        when(toState){
            ViewState.DEFAULT -> {
                selectViewContainer.visibility = GONE
            }
            ViewState.DEFAULT_TO_SELECT -> {
                animationInterface.onShowAnimation(itemView,selectViewContainer)
            }
            ViewState.SELECT -> {
                selectViewContainer.visibility = VISIBLE
            }
            ViewState.SELECT_TO_DEFAULT -> {
                animationInterface.onHideAnimation(itemView,selectViewContainer)
            }
        }
    }



    fun Float.toPx(context: android.content.Context): Int {
        return android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_DIP, this, context.resources.getDisplayMetrics()).toInt();
    }
}
