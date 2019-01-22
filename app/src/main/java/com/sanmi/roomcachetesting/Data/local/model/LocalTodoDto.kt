package com.sanmi.roomcachetesting.Data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "todo_table")
data class  LocalTodoDto(

        @NonNull
        @PrimaryKey
        var id: Int? = 0,

        var userId: Int? = 0,


        var title: String? = "",


        var completed: Boolean? = false
)