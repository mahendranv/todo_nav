package com.ex2.navcomponent

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewbinding.ViewBinding

fun TextView.markDone() {
    paintFlags = paintFlags or (Paint.STRIKE_THRU_TEXT_FLAG)
}

fun TextView.markNotDone() {
    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun View.layoutInflater() = LayoutInflater.from(context)