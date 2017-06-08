package com.goyourfly.multiselectadapter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById(R.id.style1).setOnClickListener {
            startActivity(Intent(it.context, EmailActivity::class.java))
        }

        findViewById(R.id.style2).setOnClickListener {
            startActivity(Intent(it.context, EmailActivity2::class.java))
        }

        findViewById(R.id.style3).setOnClickListener {
            startActivity(Intent(it.context, EmailActivity3::class.java))
        }
    }

}
