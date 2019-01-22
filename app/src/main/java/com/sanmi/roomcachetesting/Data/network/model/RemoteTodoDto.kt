package com.sanmi.roomcachetesting.Data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mobandme.android.transformer.compiler.Mappable
import com.mobandme.android.transformer.compiler.Mapped
import com.sanmi.roomcachetesting.Data.local.model.LocalTodoDto


@Mappable(with = LocalTodoDto::class)
class RemoteTodoDto(

    @Expose
    @Mapped
    var userId: Int? = 0,

    @SerializedName("id")
    @Expose
    @Mapped
    var id: Int? = 0,

    @SerializedName("title")
    @Expose
    @Mapped
    var title: String? = "",

    @SerializedName("completed")
    @Expose
    @Mapped
    var completed: Boolean? = false

)
