package com.midrive.learnerapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Lesson(@PrimaryKey var id: String = "",
                  var location: String? = "",
                  var startDate: Date? = null,
                  var thisLessonNotes: String? = "",
                  var nextLessonNotes: String? = "",
                  var mapUrl: String? = "",
                  var status: String = "",
                  var duration: Int = 0) : RealmObject()

fun Lesson.isComplete() = status == "complete"