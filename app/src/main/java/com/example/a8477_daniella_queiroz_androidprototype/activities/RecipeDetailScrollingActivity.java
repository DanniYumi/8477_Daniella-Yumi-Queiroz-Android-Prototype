package com.example.a8477_daniella_queiroz_androidprototype.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.a8477_daniella_queiroz_androidprototype.service.ProductsList;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a8477_daniella_queiroz_androidprototype.R;

public class RecipeDetailScrollingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ProductsList productsList;
    Integer rate=0;
    Button backToAddRecipebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail_scrolling);


        ImageView recipeImageView = findViewById(R.id.recipeImageViewDetailActivity);
        TextView RecipeNameTextView = findViewById(R.id.RecipeNameTextViewDetailActivity);
        ratingBar = findViewById(R.id.reciperRatingBarDetailActivity);
        Button backToAddRecipebutton=findViewById(R.id.backToAddRecipebutton);

        backToAddRecipebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntentWithData();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //set the rate the user selected
                rate = (int)ratingBar.getRating();
            }
        });

        //check if everything is ok until here
        Intent intentThatCalled = getIntent();
        if(intentThatCalled.hasExtra(ProductsList.RECIPE_KEY)){
            productsList =(ProductsList) intentThatCalled.getSerializableExtra(ProductsList.RECIPE_KEY);
            RecipeNameTextView.setText(productsList.getRecipeName());

            View rootView = recipeImageView.getRootView();
            //find the image and select the image
            int resID = rootView.getResources().getIdentifier(productsList.getProductImage(), "drawable", rootView.getContext().getPackageName());
            recipeImageView.setImageResource(resID);
        }
    }
    //when you press the toolbar is going to send you back to the recipe list
    @Override
    public void onBackPressed() {
        setIntentWithData();
        super.onBackPressed();
    }

    private void setIntentWithData() {
        Intent goingBack=new Intent();
        //set recipe information
        goingBack.putExtra(ProductsList.RECIPE_KEY, productsList);
        goingBack.putExtra(ProductsList.RECIPE_DIFFICULTY, rate);

        setResult(RESULT_OK, goingBack);
        finish();

    }
}