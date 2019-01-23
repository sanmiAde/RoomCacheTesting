package com.sanmi.roomcachetesting.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.widget.Toast
import com.sanmi.roomcachetesting.Data.network.NetWorkState
import com.sanmi.roomcachetesting.R
import com.sanmi.roomcachetesting.ui.adapter.TodoListAdapter

import android.net.ConnectivityManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        observeNetworkState()

        val adapter = initRecyclerView()

        if (isNetworkAvailable()) {
            getTodosOnline(adapter)

        } else {
            showProgressBar()
            getTodosOffline(adapter)
            hideProgressBar()
        }


    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    private fun getTodosOnline(adapter: TodoListAdapter) {
        viewModel.initApiCall("todos").observe(this, Observer { it ->
            adapter.setTodoList(it)

        })
    }

    private fun getTodosOffline(adapter: TodoListAdapter) {
        viewModel.getData().observe(this, Observer { it ->
            adapter.setTodoList(it)

        })
    }

    private fun observeNetworkState() {
        viewModel.observeNetworkState().observe(this, Observer { network: NetWorkState? ->

            when (network) {
                is NetWorkState.NotLoaded -> {
                    Toast.makeText(this, "Not loaded", Toast.LENGTH_SHORT).show()
                }

                is NetWorkState.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    showProgressBar()
                }

                is NetWorkState.Success -> {
                    hideProgressBar()
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }

                is NetWorkState.Error -> {
                    val netWorkError: NetWorkState.Error = network
                    Toast.makeText(this, netWorkError.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerView(): TodoListAdapter {
        val adapter = TodoListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.todo_list_recycleview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return adapter
    }

    private  fun showProgressBar(){
        loading_progressbar.visibility = View.VISIBLE
        todo_list_recycleview.visibility = View.INVISIBLE
    }


    private  fun hideProgressBar() {
        loading_progressbar.visibility = View.INVISIBLE
        todo_list_recycleview.visibility = View.VISIBLE
    }



}
