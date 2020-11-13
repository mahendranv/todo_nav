package com.ex2.navcomponent

import android.app.Activity
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.children
import com.ex2.navcomponent.data.Todo
import com.google.android.material.textfield.TextInputLayout

fun TextView.markDone() {
    paintFlags = paintFlags or (Paint.STRIKE_THRU_TEXT_FLAG)
}

fun TextView.markNotDone() {
    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun TextInputLayout.getTrimmedText(): String {
    return editText?.text.toString().trim()
}

fun View.layoutInflater(): LayoutInflater = LayoutInflater.from(context)


fun Todo.titleTransitionName() = "todo_${id}_title"

fun TextInputLayout.showKeyboard() {
    editText?.showKeyboard()
}

fun TextView.showKeyboard() {
    postDelayed({
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 200)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0);
}