package com.example.a8477_daniella_queiroz_androidprototype.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a8477_daniella_queiroz_androidprototype.R;
import com.example.a8477_daniella_queiroz_androidprototype.service.ProductsList;
import com.google.android.material.snackbar.Snackbar;

public class recipeViewHolder extends RecyclerView.ViewHolder {

    public final ImageView recipeImageImageView;
    public final TextView recipeNameTextView;
    public final TextView ingredientstTextView;
    public final TextView votesTextView;
    public final RatingBar recipeRatingBar;
    public final Button prepareTextView;
    private OnRecipeListener onRecipeListener;


    public recipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);

        recipeImageImageView = itemView.findViewById(R.id.recipeImageImageView);
        recipeNameTextView= itemView.findViewById(R.id.recipeNameTextView);
        ingredientstTextView = itemView.findViewById(R.id.ingredientstTextView);
        votesTextView = itemView.findViewById(R.id.votesTextView);
        recipeRatingBar= itemView.findViewById(R.id.votesRatingBar);
        prepareTextView = itemView.findViewById(R.id.prepareTextView);

        this.onRecipeListener = onRecipeListener;
    }
    //it's going to pass the information to the recycler view
    public void updateRecipe(ProductsList productsList){
        recipeNameTextView.setText(productsList.getRecipeName());
        ingredientstTextView.setText(productsList.getIngredients());
        votesTextView.setText(productsList.getVotes() +"Votes");

        View rootview=recipeImageImageView.getRootView();

        int resID = rootview.getResources().getIdentifier(productsList.getProductImage(), "drawable", rootview.getContext().getPackageName());
        recipeImageImageView.setImageResource(resID);
        this.recipeNameTextView.setText(productsList.getRecipeName());
        this.ingredientstTextView.setText(productsList.getIngredients());
        this.votesTextView.setText(productsList.getVotes()+" Votes");

        float rate;
        if(productsList.getVotes()>0){
            rate = 1.0f * productsList.getDifficulty() / productsList.getVotes();
        }
        else{
            rate=0;
        }
        recipeRatingBar.setRating(rate);
    }
    public void bind(ProductsList productsList, OnRecipeListener onRecipeListener){
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecipeListener.onRecipeClick(productsList);
            }
        });

        prepareTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(prepareTextView.getRootView(), "How to prepare: "+ productsList.getRecipeName() +" You will need " + productsList.getIngredients(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
