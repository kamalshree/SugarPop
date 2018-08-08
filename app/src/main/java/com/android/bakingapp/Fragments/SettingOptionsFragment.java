package com.android.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bakingapp.Adapters.StepAdapter;
import com.android.bakingapp.Interface.GetDataService;
import com.android.bakingapp.Models.StepData;
import com.android.bakingapp.R;
import com.android.bakingapp.Retrofit.RetrofitClientInstance;

import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by kamalshree on 8/5/2018.
 */

public class SettingOptionsFragment extends Fragment {

    public interface OnOptionClickListener {
        void onOptionSelected(String option);
    }
    Boolean twoPane;
    private List<StepData> stepList;
    private StepAdapter adapter;
    private RecyclerView recyclerView;
    private OnOptionClickListener mCallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnOptionClickListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement SettingOptionsFragment.OnOptionClickListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredients_item, container, false);
        twoPane=getArguments().getBoolean("isTwoPane");
        RelativeLayout mIngredientOption = rootView.findViewById(R.id.rl_ingredients);

        mIngredientOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onOptionSelected(getResources().getString(R.string.ingredients));
            }
        });


        /* step View  Implementation */
        stepList = getActivity().getIntent().getParcelableArrayListExtra("steps");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

          /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<StepData>> call = service.getAllSteps();
        call.enqueue(new Callback<List<StepData>>() {
            @Override
            public void onResponse(Call<List<StepData>> call, Response<List<StepData>> response) {
                adapter = new StepAdapter(getContext(), stepList,twoPane);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<StepData>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.retrofit_error), Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}