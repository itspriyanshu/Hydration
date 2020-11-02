package com.example.demo.Models

import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users constructor(
    @PrimaryKey
    var id: String,
    var name: String,
    var age: Int,
    var bio: String
)
