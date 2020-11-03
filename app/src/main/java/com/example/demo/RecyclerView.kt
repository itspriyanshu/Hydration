package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Dao.drinkDatabase
import com.example.demo.Dao.getDrinkDatabaseInstance
import com.example.demo.Models.DrinkTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerView : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var database: drinkDatabase
    lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        database = getDrinkDatabaseInstance(this)
        scope = CoroutineScope(Dispatchers.IO)
        var context = this
        scope.launch {
            var res = fetch()
            recyclerView = findViewById(R.id.rc_view)
            recyclerView.adapter = RCView(res, context)
        }
    }

    suspend private fun fetch(): List<DrinkTime> {
        var list = database.dao.getalldrinks()
        return list
    }
}