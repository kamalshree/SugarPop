package com.android.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.Models.RecipeData;
import com.android.bakingapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.CustomViewHolder> {

    private List<IngredientData> dataList;
    private Context context;

    public IngredientsAdapter(Context context, List<IngredientData> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView ingredients_name;
        TextView ingredients_quantity;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            ingredients_name = mView.findViewById(R.id.tv_ingredients_name);
            ingredients_quantity = mView.findViewById(R.id.tv_quantity);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredients_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.ingredients_name.setText(dataList.get(position).getIngredient());
        holder.ingredients_quantity.setText("Servings :"+dataList.get(position).getQuantity()+" "+dataList.get(position).getMeasure());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}