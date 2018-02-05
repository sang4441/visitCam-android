package com.example.ezequiel.camera2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

public class ChooseModeActivity extends Activity{

    RelativeLayout mViewCamera, mViewAnalysis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewCamera = findViewById(R.id.view_camera);
        mViewAnalysis = findViewById(R.id.view_analysis);

        mViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseModeActivity.this, MainActivity.class));
            }
        });

        mViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseModeActivity.this, AnalysisActivity.class));

            }
        });
    }
}
