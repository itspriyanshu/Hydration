package com.example.demo.ViewModel

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import com.example.demo.Dao.getDrinkDatabaseInstance
import com.example.demo.MainActivity
import com.example.demo.Models.CustomResponse
import com.example.demo.Models.DrinkTime
import com.example.demo.NetworkUtils.NetworkExecutor
import com.example.demo.NetworkUtils.OlaMoneyRequest
import com.example.demo.R
import com.example.demo.Repository.Repository
import com.example.demo.Services.NotificationServices
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class mainViewModel(var application: Application) : ViewModel() {

    private val database = getDrinkDatabaseInstance(application.applicationContext)
    private val repository = Repository(database)

    var _drinks: LiveData<List<DrinkTime>?> = MutableLiveData()
    var drinks: LiveData<List<DrinkTime>?> = _drinks

    private val _water_count = MutableLiveData<Int>()
    val water_count: LiveData<Int> = _water_count


    val time_list = ArrayList<String>()
    var jobDispatcher: Job = Job()

    init{
        _water_count.value = 0
        viewModelScope.launch {
            _water_count.value = fetch()
        }
    }

    // Working Retrofit
    fun addonecount(){
        var request: OlaMoneyRequest? = OlaMoneyRequest.Builder().setURL("https://jsonplaceholder.typicode.com/posts/1").build()
        Log.i("Call", request.toString())
        request?.let{
            Log.i("Call", "Initiating call")
            NetworkExecutor(CustomResponse::class.java).makeAsyncCall(request.createCall())
        }
        viewModelScope.launch{
            var result = add_new_data()
            if(result.equals("success")){
                _water_count.value = _water_count.value?.plus(1);
            }
            Log.i("Result of Adding a User", result)
        }
    }

    suspend fun add_new_data(): String{
        return withContext(Dispatchers.IO){
            var new_entry = DrinkTime(0,Date().toString(),1)
            var res = database.dao.add(new_entry)
            "success"
        }
    }

    suspend fun fetch(): Int{
        return withContext(Dispatchers.IO){
            var list = database.dao.getalldrinks()
            list.size
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



    fun clearAll() {
        CoroutineScope(Dispatchers.IO + jobDispatcher).launch{
            var res = repository.deleteAll()
            withContext(Dispatchers.Main){
                _water_count.value = 0
            }
            Log.i("Result of Deletion",res.toString())
        }
    }
}