package com.example.ezequiel.camera2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kimsanghwan on 2017-02-28.
 */

public class Sharedpreference {
    public static final String SHARED_PREFERENCE_NAME = "vision";
    public static SharedPreferences sharedpreferences;

    public static int getViewMode(Context context) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedpreferences.getInt("viewMode", 0);
    }

    public static void setViewMode(Context context, int viewMode) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sharedpreferences.edit().putInt("viewMode", viewMode).commit();
    }
    public static String getUserId(Context context) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedpreferences.getString("userId", "0");
    }

    public static void setUserId(Context context, String userId) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sharedpreferences.edit().putString("userId", userId).commit();
    }
}
