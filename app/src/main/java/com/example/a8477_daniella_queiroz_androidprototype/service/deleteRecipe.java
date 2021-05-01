package com.example.a8477_daniella_queiroz_androidprototype.service;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a8477_daniella_queiroz_androidprototype.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class deleteRecipe extends AppCompatActivity {

    private DataService recipeDataService;
    private List<ProductsList> productsLists;
    private Button cancelButtonDeletePage;
    private Button deleteButton;
    private EditText deleteEditText;
    private ProductsList productsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        recipeDataService = new DataService();
        recipeDataService.init(this);

        deleteButton = findViewById(R.id.deleteButton);
        cancelButtonDeletePage = findViewById(R.id.cancelButtonDeletePage);
        deleteEditText = findViewById(R.id.deleteEditText);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });
        cancelButtonDeletePage.setOnClickListener(new View.OnClickListener() {
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



    private void delete(View v) {
        String id = deleteEditText.getText().toString();
        if(id.trim().isEmpty()){
            Snackbar.make(v, "Please insert a valid id", Snackbar.LENGTH_LONG).show();
            return;
        }
        productsList = new ProductsList();
        productsList.setId(Long.valueOf(id));
        boolean result = recipeDataService.delete(productsList);

        if(result){
            Snackbar.make(v, "The recipe id: "+ id + "was deleted", Snackbar.LENGTH_LONG).show();
        }
        else {

            Snackbar.make(v, "The recipe id: " + id + "wasn't deleted", Snackbar.LENGTH_LONG).show();
        }
    }

    }
