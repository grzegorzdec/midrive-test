package com.midrive.learnerapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

class LessonList(var lessons: List<Lesson> = emptyList())

open class Lesson(@PrimaryKey var id: String = "",
                  var location: String? = "",
                  var startDate: Date? = null,
                  var thisLessonNotes: String? = "",
                  var nextLessonNotes: String? = "",
                  var mapUrl: String? = "") : RealmObject()