package com.midrive.learnerapp.activity.lesson

import android.databinding.ViewDataBinding
import com.midrive.learnerapp.BR
import com.midrive.learnerapp.R
import com.midrive.learnerapp.databinding.LessonListContentItemBinding
import com.midrive.learnerapp.databinding.LessonListHeaderItemBinding
import com.midrive.learnerapp.model.Lesson
import java.util.*

sealed class LessonListAdapterItem : BindableAdapterLessonItem {

    class Header(private val type: LessonHeaderType) : LessonListAdapterItem() {

        override val bindingId: Int get() = BR.viewModel
        override val layoutId: Int get() = R.layout.lesson_list_header_item
        override val bindableItem by lazy {
            LessonHeaderViewModel(type)
        }
        override val order by lazy {
            Date().time
        }

        override fun onBindExtras(viewDataBinding: ViewDataBinding, adapterPosition: Int) {
            (viewDataBinding as LessonListHeaderItemBinding).apply {
                viewModel = bindableItem
                executePendingBindings()
            }
        }
    }

    class Content(private val lesson: Lesson) : LessonListAdapterItem() {

        override val bindingId: Int get() = BR.viewModel
        override val layoutId: Int get() = R.layout.lesson_list_content_item
        override val bindableItem by lazy {
            LessonContentViewModel(lesson)
        }
        override val order by lazy {
            lesson.startDate?.time ?: Date().time
        }

        override fun onBindExtras(viewDataBinding: ViewDataBinding, adapterPosition: Int) {
            (viewDataBinding as LessonListContentItemBinding).apply {
                viewModel = bindableItem
                executePendingBindings()
            }
        }
    }
}