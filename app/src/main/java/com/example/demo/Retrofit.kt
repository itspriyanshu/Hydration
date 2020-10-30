package com.example.demo

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*


var baseUrl = "https://jsonplaceholder.typicode.com"

var fit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()

interface api{
    @GET("/posts")
    fun getall(): Deferred<JsonArray>
}

object service {
    val query: api by lazy {
        fit.create(api::class.java)
    }
}
