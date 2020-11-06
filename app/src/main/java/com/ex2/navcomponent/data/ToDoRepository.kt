package com.ex2.navcomponent.data

import java.util.*


/**
 * TODO repository is a in memory data store that provides mean to add/edit/delete todo items
 */
object ToDoRepository {

    private var counter = 1

    val COLORS = arrayOf(
        0xfff44336.toInt(),
        0xffe91e63.toInt(),
        0xff9c27b0.toInt(),
        0xff673ab7.toInt(),
        0xff3f51b5.toInt(),
        0xff2196f3.toInt(),
        0xff009688.toInt(),
        0xff0091ea.toInt(),
        0xff1b5e20.toInt(),
        0xffff5722.toInt(),
        0xff263238.toInt()
    )

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
            updated = randomTime(),
            colorCode = COLORS[2]
        ),

        Todo(
            id = counter++,
            description = "Add dependencies",
            updated = randomTime(),
            colorCode = COLORS[4]
        ),

        Todo(
            id = counter++,
            description = "Create fragments",
            updated = randomTime(),
            colorCode = COLORS[6]
        ),

        Todo(
            id = counter++,
            description = "Create nav graph",
            updated = randomTime(),
            colorCode = COLORS[0]
        )
    )

    private fun randomTime(): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, 2)
        return cal.timeInMillis
    }

    fun addToDo(todo: String, color: Int = COLORS[0]): Todo {
        val todo = Todo(
            id = counter++,
            updated = System.currentTimeMillis(),
            completed = false,
            description = todo,
            colorCode = color
        )
        list.add(0, todo)
        return todo
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