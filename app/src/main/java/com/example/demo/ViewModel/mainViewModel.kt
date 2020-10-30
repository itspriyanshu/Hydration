package com.example.demo.ViewModel

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.demo.Repository.Repository
import com.example.demo.Models.Users
import com.example.demo.Dao.getDatabaseInstance
import com.example.demo.MainActivity
import com.example.demo.Models.convertToDomain
import com.example.demo.R
import com.example.demo.Services.NotificationServices
import com.example.demo.service
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class mainViewModel(var application: Application) : ViewModel() {

    private val database = getDatabaseInstance(application.applicationContext)
    private val repository = Repository(database)

    private var _users: LiveData<List<Users>?> = MutableLiveData()
    var users: LiveData<List<Users>?> = _users

    private val _water_count = MutableLiveData<Int>()
    val water_count: LiveData<Int> = _water_count


    val time_list = ArrayList<String>()
    var jobDispatcher: Job = Job()

    init{
        _water_count.value = 0
        var initloader = Job()

        viewModelScope.launch {
            var fetchresult = fetch()
        }
    }

    // Working Retrofit
    fun addonecount(){
        _water_count.value = water_count.value?.plus(1)
        time_list.add(Date().toString())
        viewModelScope.launch{
            var result = adduser()
            Log.i("Result of Adding a User", result)
        }

    }

    suspend fun adduser(): String{
        return withContext(Dispatchers.IO){
            var res = repository.addUser(
                Users(
                    "sinha",
                    "Priyanshu",
                    21,
                    "Kind soul"
                )
            )
            res
        }
    }

    suspend fun fetch(){
        withContext(Dispatchers.IO){
            _users = database.dao.getalluser()
            users = Transformations.map(_users){
                Log.i("From VM",it.toString())
                it
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

    fun pushNotification(){
        val builder = NotificationCompat.Builder(application.applicationContext, NotificationServices.channel_id)

        val intent: Intent = Intent(application, MainActivity::class.java )
        val pendingIntent = PendingIntent.getActivity(application, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentTitle("Drink Please!").setContentText("Its Time to drink a glass of water. Please go ahead and drink water.").setSmallIcon(
            R.drawable.ic_local_drink_grey_120px).setColor(Color.GRAY).setContentIntent(pendingIntent).setAutoCancel(true)


        var notificationManager = application.getSystemService(NotificationManager::class.java)
        notificationManager.notify(12,builder.build())
    }

    fun clearAll() {
        CoroutineScope(Dispatchers.IO + jobDispatcher).launch{
            var res = repository.deleteAll()
            Log.i("Result of Deletion",res.toString())
        }
    }
}