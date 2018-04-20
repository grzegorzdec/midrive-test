package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.midrive.learnerapp.helper.date.ISO8601
import com.midrive.learnerapp.model.Lesson

class LessonViewModel(val lesson: Lesson) : BaseObservable() {

    @get:Bindable
    val date
        get() = startDate

    @get:Bindable
    val time
        get() = startTime

    private val startDate by lazy {
        ISO8601.toDayMonthDate(lesson.startDate)
    }

    private val startTime by lazy {
        ISO8601.toTimeWithPrefix(lesson.startDate)
    }
}