package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.util.Log
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.StateChangeListener

class EmailActivity2 : RecyclerActivity() ,StateChangeListener{
    override fun onSelectMode() {
        "onSelectMode".log()
    }

    override fun onSelect(position: Int, selectNum: Int) {
        "onSelect,Position:$position,SelectNum:$selectNum".log()
    }

    override fun onUnSelect(position: Int, selectNum: Int) {
        "onSelect,onUnSelect:$position,SelectNum:$selectNum".log()
    }

    override fun onDone(array: ArrayList<Int>) {
        "onDone:$array".log()
    }

    override fun onCancel() {
        "onCancel".log()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(EmailAdapter())
                .stateChangeListener(this)
                .colorStyle()
                .selectId(R.id.background)
                .build()

    }

    fun String.log(){
        Log.d("EmailActivity2",this)
    }
}
