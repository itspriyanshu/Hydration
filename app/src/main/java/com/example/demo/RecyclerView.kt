package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView

class RecyclerView : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        var list = intent.extras?.get("time_list") as List<String>

        recyclerView = findViewById(R.id.rc_view)
        recyclerView.adapter = RCView(list)
    }
}