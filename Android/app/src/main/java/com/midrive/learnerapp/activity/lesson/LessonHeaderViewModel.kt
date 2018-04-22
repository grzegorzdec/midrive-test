package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable

class LessonHeaderViewModel(private val type: LessonHeaderType): BaseObservable() {

    @get:Bindable
        val title
            get() = type.name
}