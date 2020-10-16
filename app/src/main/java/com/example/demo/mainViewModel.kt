package com.example.demo

import android.net.DnsResolver
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.loader.content.AsyncTaskLoader
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList

class mainViewModel : ViewModel() {

    val water_count = MutableLiveData<Int>()
    val time_list = ArrayList<String>()

    init{
        water_count.value = 0
    }

    // Working Retrofit
    fun addonecount(){
        Log.i("CustomTAG","addtocount called"+water_count.value)
        water_count.value = water_count.value?.plus(1)
        time_list.add(Date().toString())
        service.query.getall().enqueue(object : retrofit2.Callback<JsonArray>{
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.i("Google", t.toString())
            }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.i("Google", response.body().toString())
            }

        })
    }
}