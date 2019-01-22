package com.sanmi.roomcachetesting.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.sanmi.roomcachetesting.Data.Repository
import com.sanmi.roomcachetesting.Data.local.model.LocalTodoDto
import com.sanmi.roomcachetesting.Data.network.NetWorkState
import com.sanmi.roomcachetesting.Data.network.model.RemoteTodoDto

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository.getRepository(application)

    fun initApiCall(dataType: String): LiveData<List<RemoteTodoDto>> {

        return repository.initApiCall(dataType)
    }

    fun observeNetworkState(): LiveData<NetWorkState> {
        return repository.networkState
    }


    fun getdata() : LiveData<List<LocalTodoDto>>{
        return  repository.getData()
    }
}