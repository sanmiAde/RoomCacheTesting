package com.sanmi.roomcachetesting.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sanmi.roomcachetesting.Data.Repository
import com.sanmi.roomcachetesting.Data.network.NetWorkState
import com.sanmi.roomcachetesting.Data.network.model.Todo
import com.sanmi.roomcachetesting.Data.network.model.TodoResult

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository.getRepository(application)

    fun initApiCall(dataType: String): LiveData<List<Todo>> {

        return repository.initApiCall(dataType)
    }

    fun observeNetworkState(): LiveData<NetWorkState> {
        return repository.networkState
    }
}