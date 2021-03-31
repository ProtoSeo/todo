package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDatabase
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class TodoRepositoryImpl:TodoRepository {

    @Autowired
    lateinit var todoDatabase: TodoDatabase

    override fun save(todo: Todo): Todo? {
        //1. index ?
        return todo.index?.let { index ->
            // update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        }?:kotlin.run {
            // insert
            todo.apply {
                this.index = ++todoDatabase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDatabase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try{
            todoList.forEach {
                save(it)
            }
            true
        }catch (e:Exception){
            false
        }
    }

    override fun delete(index: Int): Boolean {
        return findOne(index)?.let {
            todoDatabase.todoList.remove(it)
            true
        }?:kotlin.run{
            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDatabase.todoList.first { it.index==index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDatabase.todoList
    }
}