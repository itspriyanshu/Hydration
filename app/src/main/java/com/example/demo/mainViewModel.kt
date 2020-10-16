package com.example.demo

import android.net.DnsResolver
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.loader.content.AsyncTaskLoader
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList

class mainViewModel : ViewModel() {

    val water_count = MutableLiveData<Int>()
    val time_list = ArrayList<String>()
    lateinit var jobDispatcher: Job

    init{
        water_count.value = 0
    }

    // Working Retrofit
    fun addonecount(){
        water_count.value = water_count.value?.plus(1)
        time_list.add(Date().toString())
        logThread("addonecount")
        jobDispatcher = Job()
        var scope = CoroutineScope(jobDispatcher + Dispatchers.Main)

        scope.launch {
            var def = service.query.getall()
            try{
                logThread("Couroutine")
                var result = def.await()
                Log.i("Google", result.toString())
            }catch (e: Exception){
                Log.i("Google", e.toString())
            }

        }
    }

    fun logThread(methodName : String){
        GlobalScope.launch {
            Log.i(methodName, Thread.currentThread().name.toString())
        }
    }

    override fun onCleared() {
        jobDispatcher?.cancel()
        super.onCleared()
    }
}