package com.example.demo.NetworkUtils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url
import java.util.*
import kotlin.collections.HashMap

class OlaMoneyRequest {
    lateinit var mHeaders: MutableMap<String, String>
    lateinit var mQueryParams: MutableMap<String, String>
    lateinit var mRetrofit: Retrofit
    var mURL: String? = null;

    fun createCall(): Call<ResponseBody>{
        return mRetrofit.create(ApiService::class.java).executor(mURL!!, mHeaders, mQueryParams)
    }

    public class Builder {
        var mHeaders: MutableMap<String, String> = HashMap()
        var mQueryParams: MutableMap<String, String> = HashMap()
        var body: Objects? = null;
        var mURL: String? = null;

        fun addHeader(key: String, value: String): Builder{
            this.mHeaders.put(key, value)
            return this
        }

        fun addQuery(key: String, value: String): Builder{
            this.mQueryParams.put(key, value)
            return this
        }

        fun setURL(url: String): Builder{
            this.mURL = url
            return this
        }

        fun build(): OlaMoneyRequest?{
            mURL?.let {
                var olaMoneyRequest = OlaMoneyRequest()
                olaMoneyRequest.mHeaders = mHeaders
                olaMoneyRequest.mQueryParams = mQueryParams
                olaMoneyRequest.mURL = it
                olaMoneyRequest.mRetrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").
                addConverterFactory(GsonConverterFactory.create()).build()
                return olaMoneyRequest
            }
            return null
        }
    }

    interface ApiService{
        @GET
        fun executor(@Url url: String,
                     @HeaderMap headers: MutableMap<String, String>,
                     @QueryMap queryMap: MutableMap<String, String>): Call<ResponseBody>
    }
}