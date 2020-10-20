package com.example.demo.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.demo.Repository.Repository
import com.example.demo.Models.Users
import com.example.demo.Dao.getDatabaseInstance
import com.example.demo.Models.convertToDomain
import com.example.demo.service
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class mainViewModel(var application: Application) : ViewModel() {

    private val database = getDatabaseInstance(application.applicationContext)
    private val repository = Repository(database)
    var users: LiveData<List<Users>?> = MutableLiveData()
    var userlist: List<Users> = ArrayList()
    var loading_status = MutableLiveData<Boolean>()

    val water_count = MutableLiveData<Int>()
    val time_list = ArrayList<String>()
    lateinit var jobDispatcher: Job

    init{
        water_count.value = 0
        var initloader = Job()
        var scope = CoroutineScope(initloader + Dispatchers.IO)
        scope.launch {
            users = Transformations.map(repository.getalluser()){
                Log.i("Users", it.toString())
                it
//                it?.convertToDomain()
            }
           // users = repository.getalluser()
            Log.i("Get User Result",users?.value.toString())
        }
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
                adduser()
                var result = def.await()
                Log.i("Google", result.toString())
            }catch (e: Exception){
                Log.i("Google", e.toString())
            }
        }
    }

    suspend fun adduser(){
        var res = repository.addUser(
            Users(
                "go",
                "Priyanshu",
                21,
                "Kind soul"
            )
        )
        GlobalScope.launch {
            Log.i("Add User Result",res)
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