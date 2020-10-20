package com.example.demo.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinktime")
data class DrinkTime(
    @PrimaryKey
    var time: String
)