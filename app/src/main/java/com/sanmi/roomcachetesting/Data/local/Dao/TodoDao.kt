package com.sanmi.roomcachetesting.Data.local.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sanmi.roomcachetesting.Data.local.model.LocalTodoDto


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEarthquakes(localTodoDto: List<LocalTodoDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEarthquake(localTodoDto: List<LocalTodoDto>)


    @Query("SELECT * FROM  todo_table ORDER BY id ASC")
    fun loadAllEarthquakes(): LiveData<List<LocalTodoDto>>
}