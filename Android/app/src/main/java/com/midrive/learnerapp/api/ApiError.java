package com.midrive.learnerapp.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ApiError {

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public ApiError() {

    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}