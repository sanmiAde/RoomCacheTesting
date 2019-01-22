package com.sanmi.roomcachetesting.Data.local

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var sBuilder: RoomDatabase.Builder<AppDatabase>? = null
        private const val DB_NAME = "database"

        /***
         * Creates database
         * @memoryOnly: create an in memory database sBuilder if true.
         */
        @Synchronized
        fun getDatabase(context: Context, memoryOnly: Boolean): AppDatabase {
            when (sBuilder) {
                null -> sBuilder = when {
                    memoryOnly -> Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                    else -> Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                }
            }
            return sBuilder!!.build()


        }
    }
}