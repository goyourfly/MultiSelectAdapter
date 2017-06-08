package com.goyourfly.multiple.adapter

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import com.goyourfly.multiple.adapter.holder.ColorViewHolderDecorate
import com.goyourfly.multiple.adapter.holder.ExpandViewHolderDecorate
import com.goyourfly.multiple.adapter.holder.ViewHolderDecorate
import com.goyourfly.multiple.adapter.tool.DefaultPopupToolbar

/**
 * Created by gaoyufei on 2017/6/8.
 */
object MultipleSelect {

    @JvmStatic
    fun with(activity: Activity): MultipleSelectBuilder {
        return MultipleSelectBuilder(activity)
    }


    open class MultipleSelectBuilder(val activity: Activity) {

        /**
         * 用户的Adapter
         */
        var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null


        /**
         * 选择中风格，切换颜色和显示选中按钮
         */
        private var selectStyle = Style.COLOR

        /**
         * 是否使用默认控制条
         */
        private var defaultControl = true

        /**
         * 状态回调
         */
        private var stateChangeListener:StateChangeListener? = null

        /**
         * 动画时长
         */
        var duration = 300L

        class MultipleSelectColorBuilder(activity: Activity,
                                         adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?,
                                         defaultControl: Boolean,
                                         stateChangeListener: StateChangeListener?) : MultipleSelectBuilder(activity) {
            init {
                super.adapter = adapter
                super.selectStyle = Style.COLOR
                super.defaultControl = defaultControl
                super.stateChangeListener = stateChangeListener
            }

            var selectId = 0
            var defaultColor = Color.WHITE
            var selectColor = 0xFFE0E0E0.toInt()

            fun selectId(id: Int): MultipleSelectColorBuilder {
                this.selectId = id
                return this
            }

            fun defaultColor(color: Int): MultipleSelectColorBuilder {
                this.defaultColor = color
                return this
            }

            fun selectColor(color: Int): MultipleSelectColorBuilder {
                this.selectColor = color
                return this
            }
        }

        class MultipleSelectViewBuilder(activity: Activity,
                                        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?,
                                        defaultControl:Boolean,
                                        stateChangeListener: StateChangeListener?) : MultipleSelectBuilder(activity) {
            init {
                super.adapter = adapter
                super.selectStyle = Style.VIEW
                super.defaultControl = defaultControl
                super.stateChangeListener = stateChangeListener
            }

            var defaultViewLayout = 0
            var selectViewLayout = 0
            /**
             * 如果是View风格，则支持设置位置在左边还是再右边
             */
            internal var gravity = Gravity.LEFT


            fun defaultView(layout: Int): MultipleSelectViewBuilder {
                defaultViewLayout = layout
                return this
            }

            fun selectView(layout: Int): MultipleSelectViewBuilder {
                selectViewLayout = layout
                return this
            }

//            fun gravity(gravity: Int): MultipleSelectBuilder {
//                this.gravity = gravity
//                return this
//            }
        }

        fun adapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): MultipleSelectBuilder {
            this.adapter = adapter
            return this
        }

        fun colorStyle(): MultipleSelectColorBuilder {
            return MultipleSelectColorBuilder(activity, adapter,defaultControl,stateChangeListener)
        }

        fun viewStyle(): MultipleSelectViewBuilder {
            return MultipleSelectViewBuilder(activity, adapter,defaultControl,stateChangeListener)
        }


        fun withControl(use: Boolean):MultipleSelectBuilder{
            this.defaultControl = use
            return this
        }

        fun stateChangeListener(listener: StateChangeListener):MultipleSelectBuilder{
            this.stateChangeListener = listener
            return this
        }

        fun build(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
            if (adapter == null)
                throw NullPointerException("You must specific the adapter")

            var decorate: ViewHolderDecorate? = null
            if (selectStyle == Style.COLOR) {
                val builder = this as MultipleSelectColorBuilder
                decorate = ColorViewHolderDecorate(builder.selectId, builder.defaultColor, builder.selectColor)
            } else if (selectStyle == Style.VIEW) {
                val builder = this as MultipleSelectViewBuilder
                decorate = ExpandViewHolderDecorate(activity, builder.defaultViewLayout, builder.selectViewLayout,builder.gravity,duration)
            }
            var control:DefaultPopupToolbar? = null
            if(defaultControl){
                control = DefaultPopupToolbar(activity)
            }
            return MultipleAdapter(activity, adapter!!,stateChangeListener, control,decorate!!, duration)
        }

    }
}
