package com.example.assignment_android.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_android.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {

    EditText editName,editmail,editpassword,editconfirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editName=findViewById(R.id.editName);
        editmail=findViewById(R.id.editmail);
        editpassword=findViewById(R.id.editpassword);
        editconfirmpass=findViewById(R.id.editconfirmpass);
    }

    public void signin (View view){
        Intent intent=new Intent(SignupActivity.this, SignInActivity.class);
        startActivity(intent);
    }
    public void backsignin(View view){
        Intent intent=new Intent(SignupActivity.this, SignInActivity.class);
        startActivity(intent);
    }
    public void signup(View view){
        if( TextUtils.isEmpty(editName.getText())){

            editName.setError( "Name is required!" );
        }else if( TextUtils.isEmpty(editmail.getText())){

            editmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(editpassword.getText())){

            editpassword.setError( "Password is required!" );
        }else if( TextUtils.isEmpty(editconfirmpass.getText())){

            editconfirmpass.setError( "Confirm Password is required!" );
        }else if(!editpassword.getText().toString().equals(editconfirmpass.getText().toString())){

            Toast.makeText(SignupActivity.this,"Password Not Match",Toast.LENGTH_LONG).show();
        }

        else {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            ParseUser user = new ParseUser();
            user.setUsername(editName.getText().toString().trim());
            user.setEmail(editmail.getText().toString().trim());
            user.setPassword(editpassword.getText().toString().trim());
            user.put("name",editName.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progressDialog.dismiss();
                    if (e == null) {
                        Toast.makeText(SignupActivity.this, "Welocome", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignupActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }

}
