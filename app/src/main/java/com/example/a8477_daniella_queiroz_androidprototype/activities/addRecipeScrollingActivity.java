package com.example.a8477_daniella_queiroz_androidprototype.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.a8477_daniella_queiroz_androidprototype.entities.Constants;
import com.example.a8477_daniella_queiroz_androidprototype.recyclerview.recipeRecyclerViewAdapter;
import com.example.a8477_daniella_queiroz_androidprototype.service.DataService;
import com.example.a8477_daniella_queiroz_androidprototype.service.MainActivity;
import com.example.a8477_daniella_queiroz_androidprototype.service.ProductsList;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.a8477_daniella_queiroz_androidprototype.R;

import java.util.List;

public class addRecipeScrollingActivity extends AppCompatActivity {

    private Long durationLevel=0L;
    private EditText RecipeNameEditText;
    private EditText addIngredientsEditText;
    private SeekBar durationSeekBar;
    private Button addButton;
    private Button cancelButton;
    private DataService recipeDataService;
    private ProductsList productsList;
    private List<ProductsList> productsLists;
    private recipeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());


        recipeDataService = new DataService();
        recipeDataService.init(this);
        productsLists= recipeDataService.getProductsList();


        RecipeNameEditText= findViewById(R.id.RecipeNameEditText);
        addIngredientsEditText = findViewById(R.id.addIngredientsEditText);
        durationSeekBar = findViewById(R.id.durationSeekBar);
        addButton = findViewById(R.id.addButton);
        cancelButton= findViewById(R.id.cancelButtonDeletePage);

        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //it's getting the amount you insert in the duration
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                durationLevel= Long.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
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

    private void goBackMain() {
        Intent backMainActivity = new Intent(addRecipeScrollingActivity.this, MainActivity.class);
        startActivity(backMainActivity);
    }

    private void add(View v) {
        String name = RecipeNameEditText.getText().toString();
        String ingredients = addIngredientsEditText.getText().toString();

        if(name.trim().isEmpty()){
            Snackbar.make(v,"Please insert a valid name", Snackbar.LENGTH_LONG).show();
            RecipeNameEditText.getText().clear();
            RecipeNameEditText.requestFocus();
            return;
        }
            if (ingredients.trim().isEmpty()) {
                Snackbar.make(v, "Please insert the ingredients", Snackbar.LENGTH_LONG).show();
                addIngredientsEditText.getText().clear();
                addIngredientsEditText.requestFocus();
                return;
            }


        if(!name.trim().isEmpty() && !ingredients.trim().isEmpty()) {
            productsList = new ProductsList();
            productsList.setRecipeName(name);
            productsList.setIngredients(ingredients);
            productsList.setDuration(durationLevel);

            Intent goingBack = new Intent();
            goingBack.putExtra(ProductsList.RECIPE_KEY, productsList);
            setResult(RESULT_OK, goingBack);
            finish();
        }

        recipeDataService.add(productsList);


    }



}