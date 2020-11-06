package com.ex2.navcomponent.data

import java.util.*


/**
 * TODO repository is a in memory data store that provides mean to add/edit/delete todo items
 */
object ToDoRepository {

    private var counter = 1

    val list = mutableListOf(
        Todo(
            id = counter++,
            description = "Do nothing",
            updated = randomTime(),
            completed = true
        ),

        Todo(
            id = counter++,
            description = "Create sample project",
            updated = randomTime()
        ),

        Todo(
            id = counter++,
            description = "Add dependencies",
            updated = randomTime()
        ),

        Todo(
            id = counter++,
            description = "Create fragments",
            updated = randomTime()
        ),

        Todo(
            id = counter++,
            description = "Create nav graph",
            updated = randomTime()
        )
    )

    private fun randomTime(): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, 2)
        return cal.timeInMillis
    }

    fun addToDo(todo: String) {
        list.add(
            0,
            Todo(
                id = counter++,
                updated = System.currentTimeMillis(),
                completed = false,
                description = todo
            )
        )
    }

    fun markAsCompleted(id: Int, completed: Boolean) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = list.removeAt(index)
            val updatedTodo = item.copy(
                completed = completed,
                updated = System.currentTimeMillis()
            )
            if (completed) {
                list.add(updatedTodo)
            } else {
                list.add(0, updatedTodo)
            }
        }
    }

    fun deleteTodo(id: Int) {
        list.removeIf { it.id == id }
    }

}