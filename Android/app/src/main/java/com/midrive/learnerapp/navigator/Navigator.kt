package com.midrive.learnerapp.navigator

import android.content.Context
import android.content.Intent
import com.midrive.learnerapp.activity.lesson.LessonsActivity

class Navigator(val context: Context) {

    fun showLessons(){
        context.startActivity(Intent(context, LessonsActivity::class.java))
    }
}