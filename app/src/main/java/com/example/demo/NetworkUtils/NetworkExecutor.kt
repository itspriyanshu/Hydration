package com.example.demo.NetworkUtils

import android.util.Log
import com.example.demo.Models.CustomResponse
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NetworkExecutor(var clazz: Class<*>): Callback<ResponseBody> {

    fun makeAsyncCall(call: Call<ResponseBody>){
        call.enqueue(this)
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        Log.i("Faied Request", t.toString())
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        var result = ObjectMapper().readValue(response.body()?.string(), clazz)
        Log.i("Passed Request", result.toString())
    }
}