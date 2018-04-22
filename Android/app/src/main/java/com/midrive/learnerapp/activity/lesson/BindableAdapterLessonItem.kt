package com.midrive.learnerapp.activity.lesson

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

interface BindableAdapterLessonItem {

    @get:LayoutRes
    val layoutId: Int

    val bindingId: Int

    val bindableItem: Any get() = this

    val order: Long

    fun onBindExtras(viewDataBinding: ViewDataBinding, adapterPosition: Int) {}

}