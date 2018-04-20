package com.midrive.learnerapp.repository

import com.facebook.common.internal.ImmutableList
import com.midrive.learnerapp.model.Lesson
import io.realm.Realm

class RealmRepository(val realm: Realm) : Repository {

    override fun getLessons() = ImmutableList.copyOf(realm.copyFromRealm(realm.where<Lesson>(Lesson::class.java)
            .findAll()))


}