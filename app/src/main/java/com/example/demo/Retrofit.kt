package com.example.demo

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*


var baseUrl = "https://jsonplaceholder.typicode.com"

var fit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

interface api{
    @GET("/posts")
    fun getall(): Call<JsonArray>
}

object service {
    val query: api by lazy {
        fit.create(api::class.java)
    }
}
