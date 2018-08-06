package com.android.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bakingapp.R;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;


/**
 * Created by kamalshree on 8/5/2018.
 */

public class SettingOptionsFragment extends Fragment {

    public interface OnOptionClickListener {
        void onOptionSelected(String option);
    }

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

        RelativeLayout mDisplayOption = rootView.findViewById(R.id.rl_ingredients);


        mDisplayOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onOptionSelected(getResources().getString(R.string.ingredients));
            }
        });

        return rootView;
    }
}