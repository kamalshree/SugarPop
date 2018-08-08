package com.android.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.bakingapp.Models.StepData;
import com.android.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kamalshree on 8/3/2018.
 */

public class StepDetailsFrameLayout extends Fragment {

    private StepData step;
    @BindView(R.id.tv_step_description)
    TextView stepDescription;
    @BindView(R.id.my_playerView)
    PlayerView myPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private String bundleKey;

    public StepDetailsFrameLayout() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stepdetails, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            if (getArguments().containsKey("stepInfoFromActivity")) {
                bundleKey = "stepInfoFromActivity";
            } else if (getArguments().containsKey("stepInfo")) {
                bundleKey = "stepInfo";
            }
        }
        step = getArguments().getParcelable(bundleKey);

        stepDescription.setText(step.getDescription());

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());

        myPlayerView.requestFocus();
        myPlayerView.setPlayer(mExoPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "exo-player"));
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(step.getVideoURL()));

        /* Error handling*/
        if(TextUtils.isEmpty(step.getVideoURL())){
            Toast.makeText(getActivity(), getResources().getString(R.string.video), Toast.LENGTH_SHORT).show();
        }

        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);

        if (savedInstanceState != null && mExoPlayer != null) {
            mExoPlayer.seekTo(savedInstanceState.getLong("myPlayerPosition"));
            mExoPlayer.setPlayWhenReady(savedInstanceState.getBoolean("myPlayerState"));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("myPlayerPosition", mExoPlayer.getCurrentPosition());
        outState.putBoolean("myPlayerState", mExoPlayer.getPlayWhenReady());
    }

    @Override
    public void onStop() {
        super.onStop();
        myPlayerView.setPlayer(null);
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.stop();
            mExoPlayer.release();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlayerView.setPlayer(null);
    }
}