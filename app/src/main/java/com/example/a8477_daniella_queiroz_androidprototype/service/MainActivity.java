package com.example.a8477_daniella_queiroz_androidprototype.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a8477_daniella_queiroz_androidprototype.R;
import com.example.a8477_daniella_queiroz_androidprototype.activities.addRecipeScrollingActivity;

public class MainActivity extends AppCompatActivity {
    
    DataService recipeDataService;

    
        @Override
         protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent SendName = getIntent();

        String sendingName =SendName.getExtras().getString("SendName");

        TextView WelcomeTextView = findViewById(R.id.welcomTextView);
        WelcomeTextView.setText("Welcome: " + sendingName);

        Button addRecipeButton = findViewById(R.id.addRecipeButton);

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClick(v);
            }
        });

        Button viewAllbutton = findViewById(R.id.viewAllbutton);

            viewAllbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewAllClick(v);
                }
            });

            /*Button updateRecipeButton = findViewById(R.id.goToDeleteButton);

            updateRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateRecipeButton(v);
                }
            });*/

            Button goToDeletePage = findViewById(R.id.deleteButton);

            goToDeletePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteRecipeButton(v);
                }
            });
    }

    private void deleteRecipeButton(View v) {
        Intent delete= new Intent(MainActivity.this, deleteRecipe.class);
        startActivity(delete);



    }

    private void updateRecipeButton(View v) {
        Intent update= new Intent(MainActivity.this, updateRecipe.class);
        startActivity(update);


    }

    private void viewAllClick(View v) {

            Intent viewRecipes= new Intent(MainActivity.this, ShowRecipes.class);
            startActivity(viewRecipes);


    }


    private void onAddClick(View v) {

        Intent addRecipe = new Intent(MainActivity.this, addRecipeScrollingActivity.class);

        startActivity(addRecipe);


    }

         }


