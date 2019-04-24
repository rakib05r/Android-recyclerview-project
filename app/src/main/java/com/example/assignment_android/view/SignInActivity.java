package com.example.assignment_android.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_android.InterFace.LoginResultCallbacks;
import com.example.assignment_android.R;
import com.example.assignment_android.databinding.ActivitySigninBinding;
import com.example.assignment_android.viewmodel.LoginViewModelFactory;
import com.example.assignment_android.viewmodel.LoginViewModel;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class SignInActivity extends Activity{

    //ActivitySigninBinding activitySigninBinding;
    EditText editEmail,editPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //activitySigninBinding= DataBindingUtil.setContentView(this,R.layout.activity_signin);

        editEmail=findViewById(R.id.editEmail);
        editPass=findViewById(R.id.editPass);
        if(ParseUser.getCurrentUser()!=null){
            Intent intent=new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void login(View view){
        if( TextUtils.isEmpty(editEmail.getText())){

            editEmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(editPass.getText())){

            editPass.setError( "Password is required!" );
        }else {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();
            ParseUser.logInInBackground(editEmail.getText().toString(),editPass.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    progressDialog.dismiss();
                    if (parseUser != null) {
                        Toast.makeText(SignInActivity.this, "Welocome "+editEmail.getText().toString(), Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    public void signup (View view){
        Intent intent=new Intent(SignInActivity.this, SignupActivity.class);
        startActivity(intent);
    }
    public void backsignup (View view){
        Intent intent=new Intent(SignInActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public void forgot (View view){
        Intent intent=new Intent(SignInActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }
    public void sign_in(View view) {
        Intent intent=new Intent(SignInActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    /*@Override
    public void onSuccess(String message) {
        Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String message) {
        Toast.makeText(SignInActivity.this, "Error", Toast.LENGTH_LONG).show();

    }*/
}
