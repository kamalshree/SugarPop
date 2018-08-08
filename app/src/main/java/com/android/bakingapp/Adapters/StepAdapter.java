package com.android.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.bakingapp.Fragments.StepDetailsFrameLayout;
import com.android.bakingapp.Models.StepData;
import com.android.bakingapp.R;
import com.android.bakingapp.UI.StepActivity;

import java.util.List;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.CustomViewHolder> {

    private List<StepData> dataList;
    private Context context;
    private Boolean twoPane;

    public StepAdapter(Context context, List<StepData> dataList,Boolean twoPane){
        this.context = context;
        this.dataList = dataList;
        this.twoPane=twoPane;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView step_name;
        RelativeLayout relativeLayout_stepname;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            step_name = mView.findViewById(R.id.tv_step_name);
            relativeLayout_stepname = mView.findViewById(R.id.rv_stepname);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_step_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.step_name.setText(dataList.get(position).getShortDescription());

        holder.relativeLayout_stepname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(twoPane){
                    Bundle arguments = new Bundle();
                    arguments.putParcelable("stepInfo", dataList.get(holder.getAdapterPosition()));
                    StepDetailsFrameLayout fragment = new StepDetailsFrameLayout();
                    fragment.setArguments(arguments);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detailContainer, fragment)
                            .commit();
                }
                else {
                    Intent intent = new Intent(context, StepActivity.class);
                    intent.putExtra("stepInfo", dataList.get(holder.getAdapterPosition()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}