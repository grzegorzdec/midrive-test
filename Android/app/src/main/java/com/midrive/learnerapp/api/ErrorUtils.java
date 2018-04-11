package com.midrive.learnerapp.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ErrorUtils {

    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                Api.getRetrofitInstance()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }

}