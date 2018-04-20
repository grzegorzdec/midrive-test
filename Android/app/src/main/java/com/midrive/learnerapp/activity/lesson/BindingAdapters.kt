package com.midrive.learnerapp.activity.lesson

import android.databinding.BindingAdapter
import android.databinding.ObservableList
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView

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

@BindingAdapter("android:src")
fun setImageResource(imageView: AppCompatImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("android:textColor")
fun setTextColor(textView: TextView, resource: Int) {
    textView.setTextColor(resource)
}