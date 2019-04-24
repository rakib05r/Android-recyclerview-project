package com.example.assignment_android.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.assignment_android.InterFace.LoginResultCallbacks;
import com.example.assignment_android.model.LoginModel;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private LoginModel loginModel;
    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.loginModel=new LoginModel();
    }
    public TextWatcher getEmailTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginModel.setEmail(s.toString());

            }
        };
    }
    public TextWatcher getPasswordTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginModel.setPassword(s.toString());

            }
        };
    }
    public void onLoginClicked(View view){
        if (loginModel.isValidData())
            loginResultCallbacks.onSuccess("Welcome");
        else
            loginResultCallbacks.onError("Error");
    }
}
