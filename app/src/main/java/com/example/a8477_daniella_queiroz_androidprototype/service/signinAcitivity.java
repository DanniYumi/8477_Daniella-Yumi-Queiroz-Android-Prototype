package com.example.a8477_daniella_queiroz_androidprototype.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a8477_daniella_queiroz_androidprototype.R;

public class signinAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_acitivity);

        Button signinButton= findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(v);
            }
        });
    }

    private void signin(View v) {
        Intent signinButton = new Intent(signinAcitivity.this, loginMainActivity.class);
        startActivity(signinButton);
        finish();
    }
}