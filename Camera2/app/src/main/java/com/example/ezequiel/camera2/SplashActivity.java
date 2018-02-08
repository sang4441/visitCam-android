package com.example.ezequiel.camera2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ezequiel.camera2.utils.Sharedpreference;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

public class SplashActivity extends Activity {
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFERENCE_NAME = "vision";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = this.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (isLoggedIn()) {
            if (Sharedpreference.getViewMode(getApplicationContext()) == 1) {
                startActivity(new Intent(SplashActivity.this, AnalysisActivity.class));
                finish();
            } else if (Sharedpreference.getViewMode(getApplicationContext()) == 2) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

}
