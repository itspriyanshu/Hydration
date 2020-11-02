package com.example.demo.Dao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.demo.Models.DrinkTime
import com.example.demo.Models.Users

@Dao
interface dDao{

    @Query("SELECT * FROM drinktime")
    suspend fun getalldrinks(): List<DrinkTime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(drinkTime: DrinkTime)

    @Query("DELETE FROM drinktime")
    suspend fun deleteQuery(): Int

    @Query("SELECT * FROM drinktime")
    fun getlivedrinks(): LiveData<List<DrinkTime>?>
}

@Database(entities = [DrinkTime::class],version = 1)
abstract class drinkDatabase: RoomDatabase(){
    abstract val dao: com.example.demo.Dao.dDao
}

@Volatile
private lateinit var ddatabase: drinkDatabase

fun getDrinkDatabaseInstance(context: Context) : drinkDatabase {
    synchronized(drinkDatabase::class.java){
        if(!::ddatabase.isInitialized){
            ddatabase = Room.databaseBuilder(context.applicationContext, drinkDatabase::class.java, "Database").build()
        }
    }
    return ddatabase
}