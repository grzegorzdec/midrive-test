package com.midrive.learnerapp.utils

import android.databinding.ObservableArrayList
import android.databinding.ObservableList

fun <T> observableListOf(vararg items: T): ObservableList<T> {
    return ObservableArrayList<T>().apply {
        addAll(items)
    }
}