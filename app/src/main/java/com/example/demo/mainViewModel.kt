package com.example.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.loader.content.AsyncTaskLoader
import java.util.*
import kotlin.collections.ArrayList

class mainViewModel : ViewModel() {

    val water_count = MutableLiveData<Int>()
    val time_list = ArrayList<String>()

    init{
        water_count.value = 1
    }

    fun addonecount(){
        Log.i("CustomTAG","addtocount called"+water_count.value)
        water_count.value = water_count.value?.plus(1)
        time_list.add(Date().toString())
    }
}