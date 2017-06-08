package com.goyourfly.multiselectadapter

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.goyourfly.multiple.adapter.Gravity
import com.goyourfly.multiple.adapter.MultipleAdapter
import com.goyourfly.multiple.adapter.MultipleSelect
import com.goyourfly.multiple.adapter.tool.PopupToolView

class EmailActivity : RecyclerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler.adapter = MultipleSelect
                .with(this)
                .adapter(EmailAdapter())
                .viewStyle()
                .controlColor(Color.BLACK)
                .build()

        val p:PopupToolView
    }
}
