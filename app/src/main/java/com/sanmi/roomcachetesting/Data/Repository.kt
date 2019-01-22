package com.sanmi.roomcachetesting.Data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log

import com.sanmi.roomcachetesting.Data.network.JsonPlaceHolderInterface
import com.sanmi.roomcachetesting.Data.network.NetWorkState
import com.sanmi.roomcachetesting.Data.network.RetrofitInstance
import com.sanmi.roomcachetesting.Data.network.model.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {

    val networkState: MutableLiveData<NetWorkState> = MutableLiveData()
    private val TAG = Repository::class.simpleName
    private var todoResults: List<Todo>? = null


    fun initApiCall(dataType: String): LiveData<List<Todo>> {


        networkState.value = NetWorkState.NotLoaded

        val service: JsonPlaceHolderInterface = RetrofitInstance.initRetrofitInstance()
        val call: Call<List<Todo>> = service.getFakeData(dataType)
        val todoListLiveData: MutableLiveData<List<Todo>> = MutableLiveData()

        networkCall(call, todoListLiveData)

        return todoListLiveData
    }


    private fun networkCall(call: Call<List<Todo>>, todoListLiveData: MutableLiveData<List<Todo>>) {

        networkState.value = NetWorkState.Loading

        call.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>?) {
                when {
                    response?.isSuccessful!! -> {
                        networkState.value = NetWorkState.Success
                        todoListLiveData.value = response?.body()
                    }
                    else -> {
                        networkState.value = NetWorkState.Error(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Log.e(TAG, t.message)
                networkState.value = NetWorkState.Error(t.message)
            }

        })
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