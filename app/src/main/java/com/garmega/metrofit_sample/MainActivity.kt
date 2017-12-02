package com.garmega.metrofit_sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"

    private val btnAPITest = btn_api_test as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        btnAPITest.setOnClickListener( { apiButtonPress() } )
    }

    private fun apiButtonPress() {
    }
}
