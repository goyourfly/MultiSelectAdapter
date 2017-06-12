package com.goyourfly.multiselectadapter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById(R.id.demo_1).setOnClickListener {
            startActivity(Intent(it.context, Demo1Activity::class.java))
        }

        findViewById(R.id.demo_2).setOnClickListener {
            startActivity(Intent(it.context, Demo2Activity::class.java))
        }
        findViewById(R.id.demo_3).setOnClickListener {
            startActivity(Intent(it.context, Demo3Activity::class.java))
        }

        findViewById(R.id.demo_4).setOnClickListener {
            startActivity(Intent(it.context, Demo4Activity::class.java))
        }

        findViewById(R.id.demo_5).setOnClickListener {
            startActivity(Intent(it.context, Demo5Activity::class.java))
        }
    }

}
