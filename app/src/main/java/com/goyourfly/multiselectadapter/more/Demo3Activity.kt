package com.goyourfly.multiselectadapter.more

import android.os.Bundle
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.menu.SimpleDeleteSelectAllMenuBar
import com.goyourfly.multiple.adapter.viewholder.color.ColorFactory
import com.goyourfly.multiselectadapter.DemoAdapter
import com.goyourfly.multiselectadapter.R
import com.goyourfly.multiselectadapter.RecyclerActivity

class Demo3Activity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val demoAdapter = DemoAdapter()
        recycler.adapter = MultipleSelect.with(this)
                .adapter(demoAdapter)
                .decorateFactory(ColorFactory())
                .linkList(demoAdapter.list)
                .customMenu(SimpleDeleteSelectAllMenuBar(this, resources.getColor(R.color.colorPrimary)))
                .build()

    }

    fun String.log(){
        android.util.Log.d("Demo3Activity",this)
    }
}
