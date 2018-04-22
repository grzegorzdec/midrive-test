package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.midrive.learnerapp.R
import com.midrive.learnerapp.helper.date.ISO8601
import com.midrive.learnerapp.model.Lesson
import com.midrive.learnerapp.model.isComplete

class LessonContentViewModel(val lesson: Lesson) : BaseObservable() {

    @get:Bindable
    val date
        get() = startDate

    @get:Bindable
    val time
        get() = startTime

    @get:Bindable
    val statusDrawable
        get() = if (lesson.isComplete()) R.drawable.event_complete else R.drawable.event_incomplete

    @get:Bindable
    val status
        get() = " - ${lesson.status}"

    @get:Bindable
    val totalHours
        get() = "total hours: ${lesson.duration}"

    private val startDate by lazy {
        ISO8601.toDayMonthDate(lesson.startDate)
    }

    private val startTime by lazy {
        ISO8601.toTimeWithPrefix(lesson.startDate)
    }
}