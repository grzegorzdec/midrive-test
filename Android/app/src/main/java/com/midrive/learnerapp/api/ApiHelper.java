package com.midrive.learnerapp.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.midrive.learnerapp.LearnerApplication;
import com.midrive.learnerapp.activity.MainActivity;
import com.midrive.learnerapp.model.Login;
import com.midrive.learnerapp.model.User;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.HttpException;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ApiHelper {

    public static final String TAG = ApiHelper.class.getSimpleName();

    public static Observable<User> getUser(final Realm realm, final Context context,
                                         final ErrorCallback errorCallback) {
        return Api.getApi().getUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        handleUser(realm, context, user);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(errorCallback, context, throwable, realm);
                    }
                });
    }

    public static Observable<User> login(final Realm realm, final Context context, Login login,
                                         final ErrorCallback errorCallback) {
        LearnerApplication.getInstance().setAuthToken(null);
        Api.setAuthToken(null);
        return Api.getApi().login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        handleUser(realm, context, user);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(errorCallback, context, throwable, realm);
                    }
                });
    }

    /*
        Internal Helper Methods
     */

    private static void handleUser(final Realm realm, Context context, User user) {
        // create user
        LearnerApplication.getInstance().setUserId(user.getId());
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

        if (user.getAuthToken() != null && !user.getAuthToken().isEmpty()) {
            LearnerApplication.getInstance().setAuthToken(user.getAuthToken());
            Api.setAuthToken(user.getAuthToken());
        }
    }

    private static void handleError(ErrorCallback errorCallback, Context context, Throwable e) {
        handleError(errorCallback, context, e, null);
    }

    private static void handleError(ErrorCallback errorCallback,
                                    Context context, Throwable e, Realm realm) {
        try {
            Log.i(TAG, "Handling Error: ");
            e.printStackTrace();
            if (realm != null && !realm.isClosed() && realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == 401) {
                    if (LearnerApplication.getInstance().getAuthToken() == null) {
                        // If is sign in show error message
                        errorCallback.onErrorMessage(((HttpException) e).message());
                        return;
                    } else {
                        // Or reset the app
                        resetApp(context);
                        return;
                    }
                }
                ApiError error = ErrorUtils.parseError(((HttpException) e).response());
                if (error != null && error.getErrorMessage() != null) {
                    errorCallback.onApiError(error);
                } else {
                    errorCallback.onError(e);
                }
            } else if (errorCallback != null) {
                errorCallback.onError(e);
            }
        } catch (Exception ex) {
            Log.i(TAG, "Handling thrown Exception: ");
            ex.printStackTrace();
            errorCallback.onError(e);
        }
    }

    private static void resetApp(Context context) {
        resetApp(context, false);
    }

    public static void resetApp(Context context, boolean showToast) {
        if (showToast) {
            Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show();
        }

        LearnerApplication.getInstance().setAuthToken(null);
        LearnerApplication.getInstance().clearSharedPreferences();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public interface ErrorCallback {
        void onError(Throwable e);

        void onErrorMessage(String errorMessage);

        void onApiError(ApiError apiError);
    }

}
