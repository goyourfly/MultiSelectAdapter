package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.StateChangeListener

class EmailActivity3 : AppCompatActivity(), StateChangeListener {
    override fun onSelectMode() {
        control.visibility = View.VISIBLE
    }

    override fun onSelect(position: Int, selectNum: Int) {
        title.text = "当前选中 $selectNum 条"
    }

    override fun onUnSelect(position: Int, selectNum: Int) {
        title.text = "当前选中 $selectNum 条"
    }

    override fun onDone(array: ArrayList<Int>) {
        control.visibility = View.GONE
    }

    override fun onCancel() {
        control.visibility = View.GONE
    }

    val title: TextView by lazy {
        findViewById(R.id.title) as TextView
    }
    val control: View by lazy {
        findViewById(R.id.layout_control)
    }

    val recycler: RecyclerView by lazy { findViewById(R.id.recycler) as RecyclerView }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_3)
        val emailAdapter = EmailAdapter()
        val adapter = MultipleSelect
                .with(this)
                .adapter(emailAdapter)
                .stateChangeListener(this)
                .colorStyle()
                .selectId(R.id.background)
                .withControl(false)
                .build()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recycler.adapter = adapter
        findViewById(R.id.cancel).setOnClickListener {
            adapter.cancel()
        }
        findViewById(R.id.confirm).setOnClickListener {
            adapter.done()
        }
        findViewById(R.id.delete).setOnClickListener {
            val select = adapter.getSelectIndex()
            for (i in select){
                emailAdapter.deleteItem(i)
            }
            adapter.done()
        }
    }

    fun String.log() {
        Log.d("EmailActivity2", this)
    }
}
