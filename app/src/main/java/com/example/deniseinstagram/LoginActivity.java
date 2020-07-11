package com.example.deniseinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG= "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        //Navigate to main activity
    }
}
