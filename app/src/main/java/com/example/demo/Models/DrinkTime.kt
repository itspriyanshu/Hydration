package com.example.demo.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinktime")
data class DrinkTime(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var time: String,
    var glasse: Int
)