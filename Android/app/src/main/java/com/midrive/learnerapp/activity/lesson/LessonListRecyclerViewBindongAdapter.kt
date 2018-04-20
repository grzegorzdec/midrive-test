package com.midrive.learnerapp.activity.lesson

import android.databinding.BindingAdapter
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

@BindingAdapter("lessonList_lessonViewModels")
internal fun RecyclerView._bindLessonViewModel(viewModels: ObservableList<LessonViewModel>?) {
    if(layoutManager == null) {
        layoutManager = LinearLayoutManager(context)
    }

    viewModels?.let {
        adapter = LessonListAdapter(viewModels)
    }.let {
        adapter.notifyDataSetChanged()
    }
}