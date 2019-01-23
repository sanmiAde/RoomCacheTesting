package com.sanmi.roomcachetesting.Data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class RemoteTodoDto(

    @Expose

    var userId: Int? = 0,

    @SerializedName("id")
    @Expose

    var id: Int? = 0,

    @SerializedName("title")
    @Expose

    var title: String? = "",

    @SerializedName("completed")
    @Expose

    var completed: Boolean? = false

)
