package com.example.demo.Dao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.demo.Models.Users

@Dao
interface Dao{

    @Query("SELECT * FROM users")
    fun getalluser(): LiveData<List<Users>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adduser(users: Users)

    @Query("DELETE FROM users")
    suspend fun deleteQuery(): Int
}

@Database(entities = [Users::class],version = 1)
abstract class userDatabase: RoomDatabase(){
    abstract val dao: com.example.demo.Dao.Dao
}

@Volatile
private lateinit var database: userDatabase

fun getDatabaseInstance(context: Context) : userDatabase {
    synchronized(userDatabase::class.java){
        if(!::database.isInitialized){
            database = Room.databaseBuilder(context.applicationContext, userDatabase::class.java, "roomDatabase").build()
        }
    }
    return database
}