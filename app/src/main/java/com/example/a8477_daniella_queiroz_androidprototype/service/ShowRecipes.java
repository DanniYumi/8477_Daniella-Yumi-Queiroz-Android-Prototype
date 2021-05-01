package com.example.a8477_daniella_queiroz_androidprototype.service;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a8477_daniella_queiroz_androidprototype.R;
import com.example.a8477_daniella_queiroz_androidprototype.activities.RecipeDetailScrollingActivity;
import com.example.a8477_daniella_queiroz_androidprototype.activities.addRecipeScrollingActivity;
import com.example.a8477_daniella_queiroz_androidprototype.entities.Constants;
import com.example.a8477_daniella_queiroz_androidprototype.recyclerview.OnRecipeListener;
import com.example.a8477_daniella_queiroz_androidprototype.recyclerview.recipeRecyclerViewAdapter;
import com.example.a8477_daniella_queiroz_androidprototype.service.DataService;
import com.example.a8477_daniella_queiroz_androidprototype.service.MainActivity;
import com.example.a8477_daniella_queiroz_androidprototype.service.ProductsList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.a8477_daniella_queiroz_androidprototype.entities.Constants.VIEW_DETAILS_ACTIVITY_CODE;

public class ShowRecipes extends AppCompatActivity implements OnRecipeListener {

    private DataService recipeDataService;
    private ProductsList productsList;
    private View rootview;
    private RecyclerView recipeRecyclerView;
    private List<ProductsList> productsLists;
    private recipeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipes);

        rootview = findViewById(R.id.recipeRecyclerView).getRootView();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recipeRecyclerView= findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(linearLayoutManager);

        recipeDataService = new DataService();
        recipeDataService.init(this);

        //get the information from the database
        productsLists = recipeDataService.getProductsList();

        //recycler view adapter will pass the data
        adapter = new recipeRecyclerViewAdapter(productsLists, this, this);

        recipeRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewRecipe();


            }

        });
    }

    private void addNewRecipe() {
        Intent goToAddNewRecipe = new Intent(ShowRecipes.this, addRecipeScrollingActivity.class);
        startActivityForResult(goToAddNewRecipe, Constants.ADD_RECIPE_ACTIVITY_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // it comes from the add activity
        if(requestCode==Constants.ADD_RECIPE_ACTIVITY_CODE){
            if(resultCode==RESULT_OK){
                addNewRecipeData(data);
            }
        }
        if( requestCode== VIEW_DETAILS_ACTIVITY_CODE){
            if(resultCode==RESULT_OK){
                //modify in the database
                modifyRecipe(data);
            }
        }
    }

    private void modifyRecipe(Intent data) {
        Integer stars;
        Long id;

        if(data.hasExtra(ProductsList.RECIPE_KEY)&& data.hasExtra(ProductsList.RECIPE_DIFFICULTY)){
            ProductsList productsList = (ProductsList)data.getSerializableExtra(ProductsList.RECIPE_KEY);
            stars = data.getIntExtra(ProductsList.RECIPE_DIFFICULTY, 0);
            id= productsList.getId();
            if (stars > 0) {
                boolean result = recipeDataService.rateRecipe(id, stars);
                int position = adapter.getProductsLists().indexOf(productsList);
                //wil list the recipe in position
                if(position>0){
                    productsList= (ProductsList) recipeDataService.getProductsList(id);
                    adapter.replaceItem(position, productsList);

                }
            }
        }
    }

    private void addNewRecipeData(Intent data) {
        ProductsList productsList =(ProductsList) data.getSerializableExtra(ProductsList.RECIPE_KEY);
        Long result= recipeDataService.add(productsList);
        String message;
        if (result > 0) {
            message="Your recipe" + productsList.getRecipeName() +" was add";
        }
        else{
            message="Something went wrong and we coudn't add your recipe";
        }
        Snackbar.make(rootview, message, Snackbar.LENGTH_LONG ).show();
    }


    @Override
    public void onRecipeClick(ProductsList productsList) {
        showRecipeDetail(productsList);
    }

    private void showRecipeDetail(ProductsList productsList) {
        Intent goToRecipeDetail = new Intent(ShowRecipes.this, RecipeDetailScrollingActivity.class);
        //it's going to pass the recipe
        goToRecipeDetail.putExtra(ProductsList.RECIPE_KEY, productsList);
        startActivityForResult(goToRecipeDetail, VIEW_DETAILS_ACTIVITY_CODE);
    }
}




