package com.android.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

import com.android.bakingapp.Models.RecipeData;
import com.android.bakingapp.R;
import com.android.bakingapp.UI.RecipeDetailsActivity;
import com.android.bakingapp.Utils.BakingConstants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CustomViewHolder> {

    private List<RecipeData> dataList;
    private Context context;

    public RecipeAdapter(Context context,List<RecipeData> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView name;
        TextView servings;
        private ImageView RecipeImage;
        private RelativeLayout relativeLayout;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.tv_recipe_name);
            servings = mView.findViewById(R.id.tv_servings);
            RecipeImage = mView.findViewById(R.id.iv_recipeimage);
            relativeLayout = mView.findViewById(R.id.rl_recipe);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        holder.servings.setText("Servings "+dataList.get(position).getServings().toString());

        switch(dataList.get(position).getName()){
            case (BakingConstants.NUTELLA_PIE):
                Glide.with(context)
                        .load(R.drawable.pie)
                        .into(holder.RecipeImage);
                break;
            case (BakingConstants.BROWNIE):
                Glide.with(context)
                        .load(R.drawable.browniecake)
                        .into(holder.RecipeImage);
                break;
            case (BakingConstants.YELLOW_CAKE):
                Glide.with(context)
                        .load(R.drawable.cakke)
                        .into(holder.RecipeImage);
                break;
            case (BakingConstants.CHEESECAKE):
                Glide.with(context)
                        .load(R.drawable.cheese)
                        .into(holder.RecipeImage);
                break;
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("recipeName", dataList.get(holder.getAdapterPosition()).getName());
                intent.putParcelableArrayListExtra("ingredientsList", new ArrayList<Parcelable>(dataList.get(holder.getAdapterPosition()).getIngredients()));

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}