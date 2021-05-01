package com.example.a8477_daniella_queiroz_androidprototype.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a8477_daniella_queiroz_androidprototype.R;

public class updateRecipe extends AppCompatActivity {

    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
    }
    private void cancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}