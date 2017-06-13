package com.goyourfly.multiple.adapter

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.ViewGroup
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder
import com.goyourfly.multiple.adapter.viewholder.DecorateFactory
import com.goyourfly.multiple.adapter.menu.MenuController
import com.goyourfly.multiple.adapter.menu.MenuBar

/**
 * Created by gaoyufei on 2017/6/8.
 * 这是个Adapter的子类，使用装饰者模式
 * 对调用者的Adapter进行修改，增加多选
 * 功能
 */

class MultipleAdapter(val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                      val stateChangeListener: StateChangeListener?,
                      val popupToolbar: MenuBar?,
                      val ignoreType: Array<Int>?,
                      val decorateFactory: DecorateFactory,
                      val duration: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MenuController {

    var showState = ViewState.DEFAULT
    val selectIndex = SparseBooleanArray()
    var selectNum = 0
    var handler = Handler()

    init {
        popupToolbar?.initControl(this)
    }

    var run = Runnable {
        if (showState == ViewState.DEFAULT_TO_SELECT) {
            showState = ViewState.SELECT
        } else if (showState == ViewState.SELECT_TO_DEFAULT) {
            showState = ViewState.DEFAULT
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder !is BaseViewHolder) {
            adapter.onBindViewHolder(viewHolder, position)
            return
        }
        /**
         * 先调用外界的绑定ViewHolder
         */
        adapter.onBindViewHolder(viewHolder.viewHolder, position)
        /**
         * 如果被忽略，则不往下走
         */
        if (isIgnore(position))
            return

        if (selectIndex.get(position)) {
            viewHolder.selectStateChanged(SelectState.SELECT)
        } else {
            viewHolder.selectStateChanged(SelectState.UN_SELECT)
        }

        viewHolder.showStateChanged(showState)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val outerHolder = adapter.onCreateViewHolder(viewGroup, position)
        if (isIgnore(position))
            return outerHolder
        return decorateFactory.decorate(outerHolder, this)
    }

    override fun getItemId(position: Int): Long {
        return adapter.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return adapter.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return adapter.itemCount
    }

    override fun selectAll() {
        for (i in 0..adapter.itemCount - 1){
            selectIndex.put(i,true)
        }
        selectNum = adapter.itemCount
        popupToolbar?.onUpdateTitle(selectNum,getTotal())
        notifyDataSetChanged()
    }

    override fun selectNothing() {
        for (i in 0..adapter.itemCount - 1){
            selectIndex.put(i,false)
        }
        selectNum = 0
        popupToolbar?.onUpdateTitle(selectNum,getTotal())
        notifyDataSetChanged()
    }

    /**
     * 在选择模式中的点击才在这里处理
     * 正常模式的话，会传递给调用者的
     * adapter
     */
    fun onItemClick(position: Int) {
        if (isIgnore(position))
            return
        if (showState != ViewState.SELECT)
            return
        selectIndex.put(position, !selectIndex[position])
        selectNum += if (selectIndex[position]) 1 else -1
        popupToolbar?.onUpdateTitle(selectNum,getTotal())
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

    /**
     * Item长按
     */
    fun onItemLongClick(position: Int): Boolean {
        if (isIgnore(position))
            return false
        selectIndex.clear()
        if (showState == ViewState.DEFAULT) {
            selectMode(false)
            selectIndex.put(position, true)
            stateChangeListener?.onSelect(position, selectNum)
        } else if (showState == ViewState.SELECT) {
            selectNum = 0
            cancel()
        }
        notifyDataSetChanged()
        handler.postDelayed(run, duration)
        return true
    }

    /**
     * 判断这个类型是否被忽略
     */
    fun isIgnore(position: Int): Boolean {
        if (ignoreType == null)
            return false
        val type = getItemViewType(position)
        return ignoreType.contains(type)
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override fun selectMode(refresh: Boolean) {
        selectNum = 1
        showState = ViewState.DEFAULT_TO_SELECT
        popupToolbar?.show()
        popupToolbar?.onUpdateTitle(selectNum,getTotal())
        stateChangeListener?.onSelectMode()
        if (refresh)
            notifyDataSetChanged()
    }

    override fun getTotal(): Int {
        return itemCount
    }

    override fun done(refresh: Boolean) {
        if (showState == ViewState.DEFAULT)
            return
        showState = ViewState.SELECT_TO_DEFAULT
        popupToolbar?.dismiss()
        if (refresh)
            notifyDataSetChanged()
        handler.postDelayed(run, duration)
        stateChangeListener?.onDone(getSelectIndex())
        selectIndex.clear()
    }

    override fun delete(refresh: Boolean) {
        if (showState == ViewState.DEFAULT)
            return
        showState = ViewState.SELECT_TO_DEFAULT
        popupToolbar?.dismiss()
        if (refresh)
            notifyDataSetChanged()
        handler.postDelayed(run, duration)
        stateChangeListener?.onDelete(getSelectIndex())
        selectIndex.clear()
    }

    override fun getSelect(): ArrayList<Int> {
        return getSelectIndex()
    }

    override fun cancel(refresh: Boolean): Boolean {
        if (showState == ViewState.DEFAULT)
            return false
        showState = ViewState.SELECT_TO_DEFAULT
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


    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        adapter.setHasStableIds(hasStableIds)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
        super.onViewRecycled(holder)
        adapter.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder?): Boolean {
        super.onFailedToRecycleView(holder)
        return adapter.onFailedToRecycleView(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        adapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        adapter.onDetachedFromRecyclerView(recyclerView)
    }
}
