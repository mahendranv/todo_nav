package com.ex2.navcomponent.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ex2.navcomponent.data.Todo
import com.ex2.navcomponent.databinding.LiTodoItemBinding

import com.ex2.navcomponent.layoutInflater
import com.ex2.navcomponent.markDone
import com.ex2.navcomponent.markNotDone

typealias OnTodoClicked = (Todo) -> Unit

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private var items: List<Todo>? = null

    private var itemClickListener: OnTodoClicked? = null

    fun setItems(items: List<Todo>) {
        this.items = items
    }

    fun setItemClickListener(listener: OnTodoClicked?) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LiTodoItemBinding.inflate(parent.layoutInflater(), parent, false)
        return ViewHolder(binding = binding, itemView = binding.root)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.let {
            holder.bind(it[position])
        }
    }

    inner class ViewHolder(
        itemView: View,
        private val binding: LiTodoItemBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var todo: Todo? = null

        init {
            itemView.setOnClickListener {
                todo?.let {
                    itemClickListener?.invoke(it)
                }
            }
        }

        fun bind(todo: Todo) {
            this.todo = todo

            if (todo.completed) {
                binding.descriptionLabel.markDone()
            } else {
                binding.descriptionLabel.markNotDone()
            }
            binding.descriptionLabel.text = todo.description
        }
    }
}