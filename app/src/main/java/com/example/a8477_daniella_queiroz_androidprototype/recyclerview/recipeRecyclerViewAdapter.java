package com.example.a8477_daniella_queiroz_androidprototype.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a8477_daniella_queiroz_androidprototype.R;
import com.example.a8477_daniella_queiroz_androidprototype.activities.addRecipeScrollingActivity;
import com.example.a8477_daniella_queiroz_androidprototype.service.ProductsList;

import java.util.List;


public class recipeRecyclerViewAdapter extends RecyclerView.Adapter<recipeViewHolder> {

    private List<ProductsList> productsLists;
    private Context context;
    private OnRecipeListener onRecipeListener;

    //it's going to pass the recipe that was clicked
    public List<ProductsList> getProductsLists() {
        return productsLists;
    }
    public recipeRecyclerViewAdapter(List<ProductsList> productsLists, Context context, OnRecipeListener onRecipeListener) {
        this.productsLists = productsLists;
        this.context = context;
        this.onRecipeListener=onRecipeListener;
    }
    // it's going to create a view holder, every time you scroll this method is going to be called
    //it's going to be trigger every time a new recipe is add
    @NonNull
    @Override
    public recipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            // will find the file in the layout folder
        View recipeview = inflater.inflate(R.layout.recycler_item_view, parent, false);

        recipeViewHolder RecipeViewHolder = new recipeViewHolder(recipeview, onRecipeListener);

        return RecipeViewHolder;
    }
    //set the data in the view holder

    @Override
    public void onBindViewHolder(@NonNull recipeViewHolder holder, int position) {
        //it's going to know the position
        ProductsList productsList = productsLists.get(position);
        //it's going to pass the information that was add in the recipeViewHolder
        holder.updateRecipe(productsList);
        holder.bind(productsList, onRecipeListener);
    }

    @Override
    public int getItemCount() {
        return productsLists.size();
    }

    public void replaceItem(int position, ProductsList productsList) {
        productsLists.set(position, productsList);
        notifyItemChanged(position);
    }
}
