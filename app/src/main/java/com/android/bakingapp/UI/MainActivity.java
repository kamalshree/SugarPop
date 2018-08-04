package com.android.bakingapp.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.bakingapp.R;
import com.android.bakingapp.Utils.BakingConstants;
import com.android.bakingapp.Utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    @BindView(R.id.tv_logo_title)
    TextView tv_logo_title;

    @BindView(R.id.layout_noconnection)
    LinearLayout layout_noconnection;

    @BindView(R.id.refresh)
    Button refresh_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!NetworkUtils.connectionStatus(this)) {
            ShowNoInternetMessage();
            buildDialog(this).show();
        }

        else{
            loadSplashScreen();
        }


}

    private void ShowNoInternetMessage() {
        iv_logo.setVisibility(View.INVISIBLE);
        layout_noconnection.setVisibility(View.VISIBLE);
        refresh_con.setVisibility(View.VISIBLE);

    }

    /* Splash Screen Implementation */
    private void loadSplashScreen() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        }, BakingConstants.TIME_OUT);
    }

    /* No Internet Dialog */
    private AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(getString(R.string.no_internet_title));
        builder.setMessage(getString(R.string.no_internet_message));

        builder.setPositiveButton(getString(R.string.no_interent_okbutton), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });

        return builder;
    }
    }