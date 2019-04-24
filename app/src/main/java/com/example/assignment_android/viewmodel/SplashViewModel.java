package com.example.assignment_android.viewmodel;

import android.widget.ProgressBar;

import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {
    private int setProgress;
    public void doWork(ProgressBar progressBar){
        for (setProgress=20;setProgress<=100;setProgress=setProgress+20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(setProgress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
