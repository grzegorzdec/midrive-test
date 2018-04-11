package com.midrive.learnerapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.midrive.learnerapp.helper.RealmMigrationHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class LearnerApplication extends MultiDexApplication {

    private static LearnerApplication sInstance;
    private static final String KEY_PREF = "miDrive Insurance";
    private static final String KEY_PREF_TOKEN = "authToken";
    private static final String KEY_PREF_USER_ID = "userId";

    private SharedPreferences mPref;

    public static LearnerApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        sInstance = this;

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setupPreferences();
        setupRealm();
        setupCalligraphy();
        setupFresco();
    }

    private void setupPreferences() {
        mPref = this.getApplicationContext().getSharedPreferences(KEY_PREF, MODE_PRIVATE);
    }

    private void setupRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration mRealmConfig = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .migration(new RealmMigrationHelper())
                .build();
        Realm.setDefaultConfiguration(mRealmConfig);
    }

    private void setupCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gt-walsheim-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void setupFresco() {
        Fresco.initialize(this);
    }

    @SuppressLint("CommitPrefEdits")
    public void setAuthToken(String authToken) {
        SharedPreferences.Editor edit = mPref.edit();
        edit.putString(KEY_PREF_TOKEN, authToken);
        edit.commit();
    }

    public String getAuthToken() {
        return mPref.getString(KEY_PREF_TOKEN, null);
    }

    @SuppressLint("CommitPrefEdits")
    public void setUserId(String userId) {
        SharedPreferences.Editor edit = mPref.edit();
        edit.putString(KEY_PREF_USER_ID, userId);
        edit.commit();
    }

    public String getUserId() {
        return mPref.getString(KEY_PREF_USER_ID, null);
    }

    public void clearSharedPreferences() {
        mPref.edit().clear().apply();
    }
}