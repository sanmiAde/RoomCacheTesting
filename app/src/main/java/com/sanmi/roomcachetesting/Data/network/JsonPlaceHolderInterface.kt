package com.sanmi.roomcachetesting.Data.network

import com.sanmi.roomcachetesting.Data.network.model.RemoteTodoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderInterface {


    @GET("/{data}")
    fun getFakeData(@Path("data") dataType: String): Call<List<RemoteTodoDto>>

}
