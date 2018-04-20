package com.midrive.learnerapp.activity.lesson

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.midrive.learnerapp.R
import com.midrive.learnerapp.activity.BaseActivity
import com.midrive.learnerapp.databinding.ActivityLessonsBinding
import com.midrive.learnerapp.repository.RealmRepository

class LessonsActivity : BaseActivity(), RefreshStatus {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLessonsBinding>(this, R.layout.activity_lessons)
        val lessonActivityViewModel = LessonActivityViewModel(RealmRepository(mRealm), this)
        binding.viewModel = lessonActivityViewModel
        lessonActivityViewModel.refresh()
    }

    override fun refreshStated() {
        showSpinner()
    }

    override fun refreshEnded() {
        hideSpinner()
    }
}