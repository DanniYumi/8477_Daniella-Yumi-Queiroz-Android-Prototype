package com.example.a8477_daniella_queiroz_androidprototype.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a8477_daniella_queiroz_androidprototype.R;

public class loginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick(v);
            }
        });

        Button signinButton = findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinButton(v);
            }
        });
    }
    //Will send the user to the Sign in page, in case they didn't already
    private void signinButton(View v) {

        Intent Signin = new Intent(loginMainActivity.this, signinAcitivity.class);
        startActivity(Signin);

    }
    //will send the user to the Main Acitivity
    private void onLoginClick(View v) {

        EditText userNameEditText = findViewById(R.id.userNameEditText);
        String name= userNameEditText.getText().toString();

        Intent goToMainActivity = new Intent(loginMainActivity.this, MainActivity.class);
        goToMainActivity.putExtra("SendName", name);


        startActivity(goToMainActivity);


    }

}