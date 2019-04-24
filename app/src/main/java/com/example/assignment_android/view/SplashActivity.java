package com.example.assignment_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.assignment_android.R;
import com.example.assignment_android.viewmodel.SplashViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int setProgress;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);

        progressBar=findViewById(R.id.progressbarid);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                splashViewModel.doWork(progressBar);
                startApp();
            }
        });
        thread.start();
    }
    public void startApp(){
        Intent intent=new Intent(SplashActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
