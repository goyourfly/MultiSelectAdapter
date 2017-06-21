package com.goyourfly.multiselectadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

open class RecyclerActivity : AppCompatActivity() {
    val recycler: RecyclerView by lazy { findViewById(R.id.recycler) as RecyclerView }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
    }

}
