package com.midrive.learnerapp.api;

import android.util.Log;

import com.midrive.learnerapp.BuildConfig;
import com.midrive.learnerapp.LearnerApplication;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class Api extends BaseApi {

    public static final String TAG = Api.class.getSimpleName();

    private static String sAuthToken = "";
    private static Retrofit sInstance;
    private static LearnerService sApi;

    static final String API_ENDPOINT = BuildConfig.API_ENDPOINT;

    static {
        sAuthToken = LearnerApplication.getInstance().getAuthToken();
        sInstance = buildRetrofit(API_ENDPOINT, getHttpClient(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {

                if (sAuthToken == null || sAuthToken.isEmpty()) {
                    Log.i(TAG, "Auth token is null");
                    return null;
                }

                Log.i(TAG, "Auth token: " + sAuthToken);
                return response.request().newBuilder()
                        .header("Authorization", sAuthToken)
                        .build();
            }
        }));

        sApi = sInstance.create(LearnerService.class);
    }

    public static Retrofit getRetrofitInstance() {
        return sInstance;
    }

    public static LearnerService getApi() {
        return sApi;
    }

    public static String getAuthToken() {
        return sAuthToken;
    }

    public static void setAuthToken(String authToken) {
        Api.sAuthToken = authToken;
    }

}
