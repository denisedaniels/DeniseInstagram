package com.example.deniseinstagram;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG= "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Check if the user is already logged in
        if (ParseUser.getCurrentUser()!=null) {
            goMainActivity();
        }

        //Get all id references for login
        etUsername= findViewById(R.id.etUsername);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);

        //Add listener to login button
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Log.i(TAG, "onClick lgin button");
                String username =etUsername.getText().toString();
                String password=etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user" +username);
        //Execute login on background thread
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e !=null) {
                    Log.e(TAG, "Login Issue", e);
                    Toast.makeText(LoginActivity.this,"Issue with login",Toast.LENGTH_SHORT).show();
                    return;
                }
                //If login is successful navigate to main activity
                goMainActivity();
                //Add toast to indicate that login was successful
                Toast.makeText(LoginActivity.this,"Success!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Use intent system to navigate to the new activity
    private void goMainActivity() {
        Intent i=new Intent (this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
