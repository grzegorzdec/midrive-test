package com.midrive.learnerapp.activity.lesson

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.midrive.learnerapp.BR
import com.midrive.learnerapp.api.ApiHelper
import com.midrive.learnerapp.repository.Repository
import com.midrive.learnerapp.utils.observableListOf

class LessonActivityViewModel(private val repository: Repository) : BaseObservable(), ApiHelper.onResponse {

    @Bindable
    var lessonList = observableListOf<LessonListAdapterItem>()

    @Bindable
    var loading = false

    private fun loadLessonList() {
        lessonList.clear()
        val contentItems = repository.getLessons().map { LessonListAdapterItem.Content(it) }.applyHeadersAndSort()

        lessonList.addAll(contentItems)
    }

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

private fun List<LessonListAdapterItem>.applyHeadersAndSort(): List<LessonListAdapterItem> =
        toMutableList().apply {
            add(LessonListAdapterItem.Header(LessonHeaderType.Previous))
        }.let {
            it.sortedByDescending { it.order }.toMutableList()
        }.also {
            it.add(0, LessonListAdapterItem.Header(LessonHeaderType.Upcoming))
        }