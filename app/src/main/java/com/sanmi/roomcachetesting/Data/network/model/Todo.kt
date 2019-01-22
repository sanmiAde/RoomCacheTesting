package com.sanmi.roomcachetesting.Data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Todo {

    @Expose
    val userId: Int? = null
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("completed")
    @Expose
    val completed: Boolean? = null

}
