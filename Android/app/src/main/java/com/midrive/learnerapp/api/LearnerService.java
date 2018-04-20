package com.midrive.learnerapp.api;

import com.midrive.learnerapp.model.Lesson;
import com.midrive.learnerapp.model.LessonList;
import com.midrive.learnerapp.model.Login;
import com.midrive.learnerapp.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by dylanturney on 01/08/2017.
 */

public interface LearnerService {

    @GET("/learner/account")
    Observable<User> getUser();

    @POST("/learner/login")
    Observable<User> login(@Body Login login);

    @GET("/learner/lesson")
    Observable<List<Lesson>> getLessons();
}
