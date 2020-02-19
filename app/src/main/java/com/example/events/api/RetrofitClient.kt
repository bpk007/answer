package com.codemobiles.pospos

import com.example.events.bean.EventsBean
import com.example.parsaniahardik.events.bean.UserDetailBean
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitClient {
    @GET("events")
    fun feedData(): Call<List<EventsBean>>

    @GET("users/{userName}")
    fun feedPersonalData(@Path("userName") shopID: String): Call<UserDetailBean>


    companion object {
        fun create(): RetrofitClient {
            val httpClient = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
            return retrofit.create(RetrofitClient::class.java)
        }
    }
}