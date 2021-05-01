package com.example.a8477_daniella_queiroz_androidprototype.service;

import android.content.Context;

import com.example.a8477_daniella_queiroz_androidprototype.database.ListDatabase;

import java.util.List;

public class DataService {

    private ListDatabase sqlite;

    public void connect(){

    }
    public void disconnect(){

    }

    public void init (Context context){
        sqlite = sqlite.getInstance(context);

    }

    //will insert the recipe in the database and return the recipe's id
    public Long add(ProductsList productsList){
        return sqlite.insert(productsList.getRecipeName(), productsList.getIngredients(), productsList.getDuration());

    }
    //delete the recipes
    public boolean delete(ProductsList productsList){
        return sqlite.delete(productsList.getId());
    }

    //update recipe name, ingredients and duration
    public boolean update(ProductsList productsList){
        return sqlite.update(productsList.getId(), productsList.getRecipeName(), productsList.getIngredients(), productsList.getDuration());
    }


    public List<ProductsList>getProductsList(){
        List<ProductsList>productsLists = sqlite.getProductsList();
        return productsLists;
    }

    public ProductsList getProductsList(Long id){

        return sqlite.getProductsList(id);
    }

    public boolean rateRecipe(Long id, Integer stars)
    {

        return sqlite.rateRecipe(id, stars);
    }

}
