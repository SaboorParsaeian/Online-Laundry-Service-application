package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saboor on 10/30/2017.
 */
public class ActivityWaitPlaceOrder extends AppCompatActivity {
    public static String data="";
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_place_order);

        // Bundle bundle=getIntent().getExtras();
        // final String data=bundle.getString("data");


    }
}
