package com.midrive.learnerapp.api;

import com.midrive.learnerapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class BaseApi {
    protected static HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return interceptor;
    }

    protected static OkHttpClient getHttpClient(Authenticator authenticator) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(21, TimeUnit.SECONDS)
                .readTimeout(21, TimeUnit.SECONDS)
                .addInterceptor(getInterceptor())
                .followRedirects(false)
                .followSslRedirects(false);

        if (authenticator != null) {
            builder.authenticator(authenticator);
        }

        return builder.build();
    }

    protected static Retrofit buildRetrofit(String endpoint, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
