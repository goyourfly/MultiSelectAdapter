package com.goyourfly.multiple.adapter

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.ViewGroup
import com.goyourfly.multiple.adapter.viewholder.BaseViewHolder
import com.goyourfly.multiple.adapter.viewholder.DecorateFactory
import com.goyourfly.multiple.adapter.menu.MenuController
import com.goyourfly.multiple.adapter.menu.MenuBar
import java.util.*

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
                      val list: MutableList<in Any>?,
                      val decorateFactory: DecorateFactory,
                      val duration: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MenuController {

    var showState = ViewState.DEFAULT
    val selectIndex = SparseBooleanArray()
    var selectNum = 0
    var handler = Handler()
    var recyclerView:RecyclerView? = null

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
        injectRecyclerView(viewHolder.viewHolder)
        /**
         * 先调用外界的绑定ViewHolder
         */
        adapter.bindViewHolder(viewHolder.viewHolder,position)
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

    fun injectRecyclerView(childHolder:RecyclerView.ViewHolder){
        try {
            val child = RecyclerView.ViewHolder::class
            if(recyclerView != null) {
                val mOwnerRecyclerView = child.java.getDeclaredField("mOwnerRecyclerView")
                mOwnerRecyclerView.isAccessible = true
                mOwnerRecyclerView.set(childHolder, recyclerView)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val outerHolder = adapter.onCreateViewHolder(viewGroup, viewType)
        if(ignoreType != null && ignoreType.contains(viewType)){
            return outerHolder
        }
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
        var count = 0
        for (i in 0..itemCount - 1) {
            if (!isIgnore(i)) {
                selectIndex.put(i, true)
                count ++
            }
        }
        selectNum = count
        popupToolbar?.onUpdateTitle(selectNum, getTotal())
        notifyDataSetChanged()
    }

    override fun selectNothing() {
        for (i in 0..itemCount - 1) {
            selectIndex.put(i, false)
        }
        selectNum = 0
        popupToolbar?.onUpdateTitle(selectNum, getTotal())
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
        popupToolbar?.onUpdateTitle(selectNum, getTotal())
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
        if (showState == ViewState.DEFAULT) {
            selectMode(false)
            selectIndex.put(position, true)
            selectNum = 1
            stateChangeListener?.onSelect(position, selectNum)
            popupToolbar?.onUpdateTitle(selectNum, getTotal())
        } else if (showState == ViewState.SELECT) {
            selectNum = 0
            cancel(false)
        }
        notifyDataSetChanged()
        return true
    }

    /**
     * 判断这个类型是否被忽略
     */
    fun isIgnore(position: Int): Boolean {
        if (ignoreType == null
                || ignoreType.isEmpty())
            return false
        val type = getItemViewType(position)
        return ignoreType.contains(type)
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override fun selectMode(refresh: Boolean) {
        selectIndex.clear()
        showState = ViewState.DEFAULT_TO_SELECT
        popupToolbar?.show()
        popupToolbar?.onUpdateTitle(selectNum, getTotal())
        if (refresh)
            notifyDataSetChanged()
        stateChangeListener?.onSelectMode()
        handler.postDelayed(run, duration)
    }

    override fun getTotal(): Int {
        val count = (0 until itemCount).count { !isIgnore(it) }
        return count
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
        handler.postDelayed(run, duration)

        // 先回调在删除
        stateChangeListener?.onDelete(getSelectIndex())
        val select = getSelectIndex()
        Collections.reverse(select)
        for (index in select) {
            list?.removeAt(index)
        }
        selectIndex.clear()
        if (refresh)
            notifyDataSetChanged()
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
        for (i in 0 until itemCount) {
            if (selectIndex[i]) {
                list.add(i)
            }
        }
        return list
    }

    fun setData(data: Any) {
        if (list != null) {
            list.clear()
            list.add(data)
        } else {
            throw NullPointerException("Please call MultipleSelect.BindList before setData")
        }
    }

    fun setData(data: List<Any>) {
        if (list != null) {
            list.clear()
            list.addAll(data)
        } else {
            throw NullPointerException("Please call MultipleSelect.BindList before setData")
        }
    }

    fun addData(data: Any) {
        if (list != null) {
            list.add(data)
        } else {
            throw NullPointerException("Please call MultipleSelect.BindList before addData")
        }
    }

    fun addData(data: List<Any>) {
        if (list != null) {
            list.addAll(data)
        } else {
            throw NullPointerException("Please call MultipleSelect.BindList before addData")
        }
    }


    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        adapter.setHasStableIds(hasStableIds)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        adapter.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return adapter.onFailedToRecycleView(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        adapter.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        adapter.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }
}
