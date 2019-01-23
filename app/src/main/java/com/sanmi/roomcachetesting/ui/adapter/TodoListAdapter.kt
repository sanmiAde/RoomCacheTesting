package com.sanmi.roomcachetesting.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sanmi.roomcachetesting.Data.local.model.LocalTodoDto
import com.sanmi.roomcachetesting.databinding.TodoListItemBinding
import com.sanmi.roomcachetesting.ui.adapter.TodoListAdapter.ViewHolder

class TodoListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>(){

    private var todoList: List<LocalTodoDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding = TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =  todoList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val book: LocalTodoDto? = todoList?.get(p1)
        holder.todoListItemBinding.todo = book
    }

    fun setTodoList(todos : List<LocalTodoDto>?) {
        todoList = todos
        notifyDataSetChanged()
    }

    inner class ViewHolder(val todoListItemBinding: TodoListItemBinding) : RecyclerView.ViewHolder(todoListItemBinding.root)

}
