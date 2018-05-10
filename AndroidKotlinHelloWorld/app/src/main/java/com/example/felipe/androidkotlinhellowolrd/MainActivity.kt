package com.example.felipe.androidkotlinhellowolrd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_hw.setOnClickListener {
            //tv_hw.text = (tv_hw.text == "") ? "Hello World!" : ""
            // Nâo tem operador ternário... -.-

            tv_hw.text = if (tv_hw.text == "") "Hello World!" else ""
            // Até que serve
        }
    }

}
