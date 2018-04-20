package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.midrive.learnerapp.BR
import com.midrive.learnerapp.api.ApiHelper
import com.midrive.learnerapp.model.Lesson
import com.midrive.learnerapp.repository.Repository
import com.midrive.learnerapp.utils.observableListOf

class LessonActivityViewModel(private val repository: Repository) : BaseObservable(), ApiHelper.onResponse {

    @Bindable
    var lessonList = observableListOf<LessonViewModel>()

    @Bindable
    var loading = false

    private fun loadLessonList() {
        lessonList.clear()
        val elements = repository.getLessons().map { it.toViewModel() }
        lessonList.addAll(elements)
    }

    private fun Lesson.toViewModel() = LessonViewModel(this)

    fun refresh() {
        loading = true
        ApiHelper.downloadLessons(repository, this) //todo inject
    }

    override fun onCompleate() {
        loadLessonList()
        loading = false
        notifyPropertyChanged(BR.loading)
    }

}