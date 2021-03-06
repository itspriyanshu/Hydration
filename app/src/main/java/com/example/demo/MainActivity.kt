package com.example.demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo.Services.NotificationServices
import com.example.demo.ViewModel.mainViewModel
import com.example.demo.ViewModel.vmFactory

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
        var viewModelProvider = vmFactory(application)
        viewModel = ViewModelProvider(this, viewModelProvider).get(mainViewModel::class.java)
        NotificationServices.getInstance(this).CreateAndRegisterChannel();


        viewModel.water_count.observe(this, Observer { count ->
            updateUI(count)
        })

        viewModel.users.observe(this, Observer {
            it?.let{
                Log.i("Viewing Users", it.toString())
            }
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
            R.id.clear -> {
                viewModel.clearAll()
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