package com.sanmi.roomcachetesting.Data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "todo_table")
data class Todo(

        @NonNull
        @PrimaryKey
        val id: Int? = null,

        val userId: Int? = null,


        val title: String? = null,


        val completed: Boolean? = null
)