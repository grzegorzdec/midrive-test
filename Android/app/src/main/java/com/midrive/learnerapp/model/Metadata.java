package com.midrive.learnerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by dylanturney on 03/08/2017.
 */

public class Metadata extends RealmObject {

    @SerializedName("bio")
    @Expose
    private String bio;

    public Metadata() {

    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
