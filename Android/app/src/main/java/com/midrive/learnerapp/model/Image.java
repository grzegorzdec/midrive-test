package com.midrive.learnerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by dylanturney on 03/08/2017.
 */

public class Image extends RealmObject {

    @SerializedName("base64")
    @Expose
    @Required
    private String base64;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
