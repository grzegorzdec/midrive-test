package com.midrive.learnerapp.repository

import com.midrive.learnerapp.model.Lesson

interface Repository {
    fun getLessons(): List<Lesson>
}