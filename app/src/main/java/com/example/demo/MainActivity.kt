package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var imageButton: ImageButton
    lateinit var textView: TextView
    lateinit var viewModel: mainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton = findViewById(R.id.ib_water_increment)
        imageButton.setOnClickListener(this)
        textView = findViewById(R.id.tv_water_count)
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)

        viewModel.water_count.observe(this, Observer { count ->
            updateUI(count)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.next_screen -> {
                var intent: Intent = Intent(this, RecyclerView::class.java)
                intent.putExtra("time_list",viewModel.time_list)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    private fun updateUI(value: Int){
        textView.setText(value.toString())
    }

    override fun onClick(v: View?) {
        var id = v?.id
        if(id == R.id.ib_water_increment){
            viewModel.addonecount()
        }
    }
}