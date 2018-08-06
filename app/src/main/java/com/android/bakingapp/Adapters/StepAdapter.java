package com.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.Models.StepData;
import com.android.bakingapp.R;

import java.util.List;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.CustomViewHolder> {

    private List<StepData> dataList;
    private Context context;

    public StepAdapter(Context context, List<StepData> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView step_name;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            step_name = mView.findViewById(R.id.tv_step_name);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.step_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.step_name.setText(dataList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}