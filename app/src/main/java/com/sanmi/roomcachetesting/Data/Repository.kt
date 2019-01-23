package com.sanmi.roomcachetesting.Data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sanmi.roomcachetesting.Data.local.AppDatabase
import com.sanmi.roomcachetesting.Data.local.Dao.TodoDao
import com.sanmi.roomcachetesting.Data.local.model.LocalTodoDto

import com.sanmi.roomcachetesting.Data.network.JsonPlaceHolderInterface
import com.sanmi.roomcachetesting.Data.network.NetWorkState
import com.sanmi.roomcachetesting.Data.network.RetrofitInstance
import com.sanmi.roomcachetesting.Data.network.model.RemoteTodoDto
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class Repository(private val application: Application) {

    val networkState: MutableLiveData<NetWorkState> = MutableLiveData()
    private val TAG = Repository::class.simpleName
    private val todoDao: TodoDao

    init {
        val db = AppDatabase.getDatabase(application, false)
        todoDao = db.todoDao()
    }


    fun initApiCall(dataType: String): LiveData<List<LocalTodoDto>> {


        networkState.value = NetWorkState.NotLoaded

        val service: JsonPlaceHolderInterface = RetrofitInstance.initRetrofitInstance()
        val call: Call<List<RemoteTodoDto>> = service.getFakeData(dataType)


        networkCall(call)

        return getData()
    }


    private fun networkCall(call: Call<List<RemoteTodoDto>>) {

        networkState.value = NetWorkState.Loading

        call.enqueue(object : Callback<List<RemoteTodoDto>> {
            override fun onResponse(call: Call<List<RemoteTodoDto>>, response: Response<List<RemoteTodoDto>>?) {
                when {
                    response?.isSuccessful!! -> {
                        networkState.value = NetWorkState.Success

                        doAsync {
                           todoDao.insertEarthquakes(convertToDatabaseDto(response.body()!!))
                        }
                    }
                    else -> {
                        networkState.value = NetWorkState.Error(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<List<RemoteTodoDto>>, t: Throwable) {
                Log.e(TAG, t.message)
                networkState.value = NetWorkState.Error(t.message)
            }

        })
    }

    private fun convertToDatabaseDto(networkRemoteTodoDtos : List<RemoteTodoDto>): List<LocalTodoDto> {

        val localTodoDtos = mutableListOf<LocalTodoDto>()

        networkRemoteTodoDtos.forEach {

            localTodoDtos.add( LocalTodoDto(it.id, it.userId, it.title, it.completed))

        }

        return localTodoDtos

    }


    fun getData() : LiveData<List<LocalTodoDto>>{

       return todoDao.loadAllEarthquakes()
    }

    companion object {
        private var instance: Repository? = null

        @Synchronized
        fun getRepository(application: Application): Repository {
            if (instance == null) {
                instance = Repository(application)
            }
            return instance!!
        }
    }
}