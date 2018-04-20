package com.midrive.learnerapp.repository

import com.midrive.learnerapp.model.Lesson

interface Repository {
    fun updateLessons(lessons: List<Lesson>)
    fun getLessons(): List<Lesson>
}