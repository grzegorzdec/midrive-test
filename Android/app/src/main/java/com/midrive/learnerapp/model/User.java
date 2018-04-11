package com.midrive.learnerapp.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.midrive.learnerapp.LearnerApplication;
import com.midrive.learnerapp.R;
import com.midrive.learnerapp.enumerated.Gender;
import com.midrive.learnerapp.enumerated.Transmission;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class User extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("transmission")
    @Expose
    private String transmission;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("lessonCredits")
    @Expose
    private int lessonCredits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Gender getGender() {
        return Gender.valueOf(gender);
    }

    public void setGender(Gender gender) {
        this.gender = gender.toString();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Transmission getTransmission() {
        return Transmission.valueOf(transmission);
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission.toString();
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public int getLessonCredits() {
        return lessonCredits;
    }

    public void setLessonCredits(int lessonCredits) {
        this.lessonCredits = lessonCredits;
    }

    public static User getCurrentUser(Realm realm) {
        return realm.where(User.class)
                .equalTo("id", LearnerApplication.getInstance().getUserId()).findFirst();
    }
}
