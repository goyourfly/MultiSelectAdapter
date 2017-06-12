package com.goyourfly.multiple.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.binder.DecorateFactory
import com.goyourfly.multiple.adapter.binder.view.RadioBtnFactory
import com.goyourfly.multiple.adapter.menu.SimpleDoneMenuBar
import com.goyourfly.multiple.adapter.menu.MenuBar

/**
 * Created by gaoyufei on 2017/6/8.
 */
object MultipleSelect {

    @JvmStatic
    fun with(activity: Activity): Builder {
        return Builder(activity)
    }


    open class Builder(val activity: Activity) {

        /**
         * 用户的Adapter
         */
        var adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>? = null


        /**
         * 选择中风格，默认给出两种风格
         */
        private var decorateFactory: DecorateFactory? = null


        private var customMenu: MenuBar? = null

        /**
         * 状态回调
         */
        private var stateChangeListener: StateChangeListener? = null

        private var ignoreType:Array<Int>? = null
        /**
         * 动画时长
         */
        var duration = 300L


        fun adapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>): Builder {
            this.adapter = adapter as RecyclerView.Adapter<in RecyclerView.ViewHolder>
            return this
        }

        fun decorateFactory(decorateFactory: DecorateFactory): Builder {
            this.decorateFactory = decorateFactory
            return this
        }

        fun ignoreViewType(ignore: Array<Int>):Builder{
            this.ignoreType = ignore
            return this
        }

        fun customMenu(menuBar: MenuBar): Builder {
            this.customMenu = menuBar
            return this
        }

        fun stateChangeListener(listener: StateChangeListener): Builder {
            this.stateChangeListener = listener
            return this
        }

        fun build(): MultipleAdapter {
            if (adapter == null)
                throw NullPointerException("You must specific the adapter")

            if(decorateFactory == null){
                decorateFactory = RadioBtnFactory(duration = this.duration)
            }

            if (customMenu == null) {
                customMenu = SimpleDoneMenuBar(activity,0xFF3F51B5.toInt())
            }
            return MultipleAdapter(adapter!!, stateChangeListener, customMenu, ignoreType,decorateFactory!!, duration)
        }

    }
}
