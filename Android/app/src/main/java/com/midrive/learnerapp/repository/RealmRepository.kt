package com.midrive.learnerapp.repository

import com.facebook.common.internal.ImmutableList
import com.midrive.learnerapp.model.Lesson
import io.realm.Realm

class RealmRepository(val realm: Realm) : Repository {

    override fun updateLessons(lessons: List<Lesson>) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(lessons)
        realm.commitTransaction()
    }

    override fun getLessons() = ImmutableList.copyOf(realm.copyFromRealm(realm.where<Lesson>(Lesson::class.java)
            .findAll()))


}