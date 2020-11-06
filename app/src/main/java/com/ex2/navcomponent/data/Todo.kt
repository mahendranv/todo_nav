package com.ex2.navcomponent.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Todo(

    val id: Int,

    val description: String,

    val updated: Long,

    val completed: Boolean = false,

    val colorCode: Int = 0xff415dba.toInt()

) : Parcelable