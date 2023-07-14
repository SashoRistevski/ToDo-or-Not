package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var btnAddTodo: Button
    private lateinit var etTodoTitle: EditText
    private lateinit var btnDeleteTodos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodoItems = findViewById(R.id.rvTodoItems)
        todoAdapter = ToDoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter

        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo = findViewById(R.id.btnAddTodo)
        etTodoTitle = findViewById(R.id.etTodoTitle)
        btnDeleteTodos = findViewById(R.id.btnDeleteTodos)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteTodos.setOnClickListener {
            todoAdapter.deleteToDo()
        }
    }
}
