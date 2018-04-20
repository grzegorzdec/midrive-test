package com.midrive.learnerapp.model

import io.realm.RealmObject

open class GeoJSON(var lon: Long? = 0, var lat: Long? = 0) : RealmObject()