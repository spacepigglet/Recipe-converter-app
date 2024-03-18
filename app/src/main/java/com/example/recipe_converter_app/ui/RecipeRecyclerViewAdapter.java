
package com.example.recipe_converter_app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.logic.RecyclerViewInterface;

import java.util.ArrayList;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    private final Context context;
    private final ArrayList<Recipe> recipeCardModels;

    public RecipeRecyclerViewAdapter(Context context, ArrayList<Recipe> recipes, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recipeCardModels = recipes;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new RecipeRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeRecyclerViewAdapter.MyViewHolder holder, int position) {
    //assigning values to the views we created in the recycler_view_row layout file
        //based on the position of the recycler view
        holder.tvRecipeName.setText(recipeCardModels.get(position).getName());
        Bitmap image = recipeCardModels.get(position).getImage();
        if(image != null){
            holder.imageview.setImageBitmap(image);
        }

    }

    @Override
    public int getItemCount() {
        //items in total. helps with binding process
        return recipeCardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRecipeName;
        ImageView imageview;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            tvRecipeName = itemView.findViewById(R.id.textView);
            imageview = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION);{
                        recyclerViewInterface.onItemClicked(position);
                    }
                }
            });
        }
    }
}

