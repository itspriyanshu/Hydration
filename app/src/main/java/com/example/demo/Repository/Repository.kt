package com.example.demo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo.Dao.userDatabase
import com.example.demo.Models.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class Repository(private val database: userDatabase) {
    suspend fun addUser(users: Users): String{
        try{
            database.dao.adduser(users)
            return "Success"
        }catch (e: Exception){
            return e.toString()
        }

    }

    suspend fun getalluser(): LiveData<List<Users>?>{
        try{
            return database.dao.getalluser()
        }catch (e: Exception){
            Log.i("Get all User",e.toString())
            return MutableLiveData()

        }
    }


}