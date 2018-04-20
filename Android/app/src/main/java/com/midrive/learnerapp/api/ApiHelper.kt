package com.midrive.learnerapp.api

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.midrive.learnerapp.LearnerApplication
import com.midrive.learnerapp.activity.MainActivity
import com.midrive.learnerapp.model.Lesson
import com.midrive.learnerapp.model.Login
import com.midrive.learnerapp.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.HttpException

/**
 * Created by dylanturney on 01/08/2017.
 */

object ApiHelper {

    val TAG = ApiHelper::class.java.simpleName

    fun getUser(realm: Realm, context: Context,
                errorCallback: ErrorCallback): Observable<User> {
        return Api.getApi().user
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .doOnNext { user -> handleUser(realm, context, user) }
                .doOnError { throwable -> handleError(errorCallback, context, throwable, realm) }
    }

    @JvmStatic
    fun login(realm: Realm, context: Context, login: Login,
              errorCallback: ErrorCallback): Observable<User> {
        LearnerApplication.getInstance().authToken = null
        Api.setAuthToken(null)
        return Api.getApi().login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .doOnNext { user -> handleUser(realm, context, user) }
                .doOnError { throwable -> handleError(errorCallback, context, throwable, realm) }
    }

    @JvmStatic
    fun downloadLessons(realm: Realm, callback: onResponse) {
        Api.getApi().lessons.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { lessons ->
                    handleLessons(realm, callback, lessons)
                }
    }

    /*
        Internal Helper Methods
     */

    private fun handleUser(realm: Realm, context: Context, user: User) {
        // create user
        LearnerApplication.getInstance().userId = user.id
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(user)
        realm.commitTransaction()

        if (user.authToken != null && !user.authToken.isEmpty()) {
            LearnerApplication.getInstance().authToken = user.authToken
            Api.setAuthToken(user.authToken)
        }
    }

    private fun handleLessons(realm: Realm,callback: onResponse, lessons: List<Lesson>) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(lessons)
        realm.commitTransaction()
        callback.onCompleate()
    }

    private fun handleError(errorCallback: ErrorCallback?,
                            context: Context, e: Throwable, realm: Realm? = null) {
        try {
            Log.i(TAG, "Handling Error: ")
            e.printStackTrace()
            if (realm != null && !realm.isClosed && realm.isInTransaction) {
                realm.cancelTransaction()
            }
            if (e is HttpException) {
                if (e.code() == 401) {
                    if (LearnerApplication.getInstance().authToken == null) {
                        // If is sign in show error message
                        errorCallback!!.onErrorMessage(e.message())
                        return
                    } else {
                        // Or reset the app
                        resetApp(context)
                        return
                    }
                }
                val error = ErrorUtils.parseError(e.response())
                if (error != null && error.errorMessage != null) {
                    errorCallback!!.onApiError(error)
                } else {
                    errorCallback!!.onError(e)
                }
            } else errorCallback?.onError(e)
        } catch (ex: Exception) {
            Log.i(TAG, "Handling thrown Exception: ")
            ex.printStackTrace()
            errorCallback!!.onError(e)
        }

    }

    private fun resetApp(context: Context) {
        resetApp(context, false)
    }

    fun resetApp(context: Context, showToast: Boolean) {
        if (showToast) {
            Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show()
        }

        LearnerApplication.getInstance().authToken = null
        LearnerApplication.getInstance().clearSharedPreferences()
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
        realm.close()
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    interface ErrorCallback {
        fun onError(e: Throwable)

        fun onErrorMessage(errorMessage: String)

        fun onApiError(apiError: ApiError)
    }

    interface onResponse {
        fun onCompleate()
    }

}
