package com.example.demo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo.Dao.drinkDatabase
import com.example.demo.Models.DrinkTime
import java.lang.Exception

class Repository(private var database: drinkDatabase) {
    suspend fun add(drinkTime: DrinkTime): String{
        try{
            database.dao.add(drinkTime)
        }catch (e: Exception){
            return "Error Occured"
        }
        return "Successfull"
    }

    suspend fun deleteAll(): Int{
        try{
            return database.dao.deleteQuery()
        }catch (e: Exception){
            Log.i("Repository","Error in deleteing times")
        }
        return 0
    }

    suspend fun getAll(): List<DrinkTime>{
        try{
            return database.dao.getalldrinks()
        }catch (e: Exception){
            Log.i("Repository","Error in fetching results")
        }
        return ArrayList()
    }

    suspend fun getLiveAll(): LiveData<List<DrinkTime>?>{
        try{
            return database.dao.getlivedrinks()
        }catch (e: Exception){
            Log.i("Repository","Error in fetching live results")
        }
        return MutableLiveData()
    }
}