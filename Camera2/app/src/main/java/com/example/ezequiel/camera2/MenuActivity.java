package com.example.ezequiel.camera2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ezequiel.camera2.utils.Sharedpreference;

public class MenuActivity extends Activity{

    RelativeLayout mViewCamera, mViewAnalysis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
        mViewCamera = findViewById(R.id.view_camera);
        mViewAnalysis = findViewById(R.id.view_analysis);

        mViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sharedpreference.setViewMode(getApplicationContext(), 2);
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        mViewAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sharedpreference.setViewMode(getApplicationContext(), 1);
                startActivity(new Intent(MenuActivity.this, AnalysisActivity.class));
            }
        });
    }
}
