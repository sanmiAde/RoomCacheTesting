package com.sanmi.roomcachetesting.Data.local.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sanmi.roomcachetesting.Data.local.model.Todo


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEarthquakes(todo: List<Todo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEarthquake(todo: List<Todo>);


    @Query("SELECT * FROM  todo_table ORDER BY id ASC")
    fun loadAllEarthquakes(): LiveData<List<Todo>>
}