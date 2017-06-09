package com.goyourfly.multiple.adapter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.util.TypedValue
import android.view.ViewGroup
import com.goyourfly.multiple.adapter.holder.BaseViewHolder
import com.goyourfly.multiple.adapter.holder.DecorateFactory
import com.goyourfly.multiple.adapter.tool.ModeControl
import com.goyourfly.multiple.adapter.tool.MenuBar

/**
 * Created by gaoyufei on 2017/6/8.
 */

class MultipleAdapter(val activity: Activity,
                      val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                      val stateChangeListener: StateChangeListener?,
                      val popupToolbar: MenuBar?,
                      val decorateFactory: DecorateFactory,
                      val duration: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ModeControl {

    var showState = ShowState.DEFAULT
    val selectIndex = SparseBooleanArray()
    var selectNum = 0
    var handler = Handler()

    init {
        popupToolbar?.initControl(this)
    }

    var run = Runnable {
        if (showState == ShowState.DEFAULT_TO_SELECT) {
            showState = ShowState.SELECT
        } else if (showState == ShowState.SELECT_TO_DEFAULT) {
            showState = ShowState.DEFAULT
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as BaseViewHolder
        /**
         * 先调用外界的绑定ViewHolder
         */
        adapter.onBindViewHolder(holder.viewHolder, position)

        if (selectIndex.get(position)) {
            holder.selectStateChanged(SelectState.SELECT)
        } else {
            holder.selectStateChanged(SelectState.UN_SELECT)
        }

        holder.showStateChanged(showState)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val outerHolder = adapter.onCreateViewHolder(viewGroup, position)
        return decorateFactory.decorate(outerHolder, this)
    }

    override fun getItemCount(): Int {
        return adapter.itemCount
    }

    fun onItemClick(position: Int) {
        if (showState != ShowState.SELECT)
            return
        selectIndex.put(position, !selectIndex[position])
        selectNum += if (selectIndex[position]) 1 else -1
        popupToolbar?.numChanged(selectNum)
        if (selectIndex[position]) {
            stateChangeListener?.onSelect(position, selectNum)
        } else {
            stateChangeListener?.onUnSelect(position, selectNum)
        }
        if (selectNum <= 0) {
            cancel()
        } else {
            notifyItemChanged(position)
        }
    }


    override fun refresh(){
        notifyDataSetChanged()
    }

    override fun selectMode(refresh: Boolean) {
        selectNum = 1
        showState = ShowState.DEFAULT_TO_SELECT
        popupToolbar?.show()
        popupToolbar?.numChanged(selectNum)
        stateChangeListener?.onSelectMode()
        if (refresh)
            notifyDataSetChanged()
    }

    override fun getTotal(): Int {
        return itemCount
    }

    override fun done(refresh: Boolean) {
        if (showState == ShowState.DEFAULT)
            return
        showState = ShowState.SELECT_TO_DEFAULT
        popupToolbar?.dismiss()
        if (refresh)
            notifyDataSetChanged()
        handler.postDelayed(run, duration)
        stateChangeListener?.onDone(getSelectIndex())
        selectIndex.clear()
    }

    override fun getSelect(): ArrayList<Int> {
        return getSelectIndex()
    }

    override fun cancel(refresh: Boolean): Boolean {
        if (showState == ShowState.DEFAULT)
            return false
        showState = ShowState.SELECT_TO_DEFAULT
        popupToolbar?.dismiss()
        if (refresh)
            notifyDataSetChanged()
        handler.postDelayed(run, duration)
        selectIndex.clear()
        stateChangeListener?.onCancel()
        return true
    }


    fun getSelectIndex(): ArrayList<Int> {
        val list = arrayListOf<Int>()
        for (i in 0..selectIndex.size() - 1) {
            if (selectIndex[i]) {
                list.add(i)
            }
        }
        return list
    }

    fun onItemLongClick(position: Int): Boolean {
        selectIndex.clear()
        if (showState == ShowState.DEFAULT) {
            selectMode(false)
            selectIndex.put(position, true)
            stateChangeListener?.onSelect(position, selectNum)
        } else if (showState == ShowState.SELECT) {
            selectNum = 0
            cancel()
        }
        notifyDataSetChanged()
        handler.postDelayed(run, duration)
        return true
    }

    fun Float.toPx(context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.getDisplayMetrics()).toInt();
    }
}
