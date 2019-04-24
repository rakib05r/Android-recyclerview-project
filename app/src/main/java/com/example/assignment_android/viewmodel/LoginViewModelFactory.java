package com.example.assignment_android.viewmodel;

import com.example.assignment_android.InterFace.LoginResultCallbacks;
import com.example.assignment_android.view.SignInActivity;
import com.example.assignment_android.viewmodel.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModelFactory(SignInActivity loginResultCallbacks) {
        this.loginResultCallbacks = (LoginResultCallbacks) loginResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LoginViewModel(loginResultCallbacks);
    }
}
