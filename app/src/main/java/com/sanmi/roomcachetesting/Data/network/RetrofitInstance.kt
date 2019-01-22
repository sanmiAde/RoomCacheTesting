package com.sanmi.roomcachetesting.Data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

    internal fun initRetrofitInstance(): JsonPlaceHolderInterface {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(JsonPlaceHolderInterface::class.java)

    }
}