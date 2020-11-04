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

    private fun addToDo(todo: Todo) {
        list.add(0, todo)
    }

    private fun markAsCompleted(id: Int, newStatus: Boolean) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = list.removeAt(index)
            list.add(
                item.copy(
                    completed = newStatus,
                    updated = System.currentTimeMillis()
                )
            )
        }
    }

}