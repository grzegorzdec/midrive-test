package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.midrive.learnerapp.api.ApiHelper
import com.midrive.learnerapp.model.Lesson
import com.midrive.learnerapp.repository.Repository
import com.midrive.learnerapp.utils.observableListOf

class LessonActivityViewModel(private val repository: Repository, private val refreshStatus: RefreshStatus) : BaseObservable(), ApiHelper.onResponse {

    @Bindable
    var lessonList = observableListOf<LessonViewModel>()

    private fun loadLessonList() {
        lessonList.clear()
        val elements = repository.getLessons().map { it.toViewModel() }
        lessonList.addAll(elements)
    }

    private fun Lesson.toViewModel() = LessonViewModel(this)

    fun refresh() {
        refreshStatus.refreshStated()
        ApiHelper.downloadLessons(repository, this)
    }

    override fun onCompleate() {
        loadLessonList()
        refreshStatus.refreshEnded()
    }

}