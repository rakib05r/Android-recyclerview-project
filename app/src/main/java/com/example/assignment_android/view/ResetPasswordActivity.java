package com.example.assignment_android.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_android.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordActivity extends Activity {

    EditText edforgetmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        edforgetmail=findViewById(R.id.forgetmail);

    }
    public void forgetpass(View view) {
        if( TextUtils.isEmpty(edforgetmail.getText())){

            edforgetmail.setError( "Email is required!" );
        }else {
            ParseUser.requestPasswordResetInBackground(edforgetmail.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // An email was successfully sent with reset instructions.
                        Toast.makeText(ResetPasswordActivity.this, "An email have sent on Your Given Mail", Toast.LENGTH_LONG).show();
                    } else {
                        // Something went wrong. Look at the ParseException to see what's up.
                        Toast.makeText(ResetPasswordActivity.this, "Something Wrong", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    public void backsigninfromreset(View view){
        Intent intent=new Intent(ResetPasswordActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
