package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun strikeToggle(tvToDoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemChanged(todos.size - 1)
    }

    fun deleteToDo() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTodoTitle).text = currentTodo.title
            findViewById<CheckBox>(R.id.cbDone).isChecked = currentTodo.isChecked
            strikeToggle(findViewById(R.id.tvTodoTitle), currentTodo.isChecked)
            findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener { _, isChecked ->
                strikeToggle(findViewById(R.id.tvTodoTitle), isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }
}
