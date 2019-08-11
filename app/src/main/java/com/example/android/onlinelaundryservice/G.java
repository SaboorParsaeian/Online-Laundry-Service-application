package com.example.android.onlinelaundryservice;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class G extends Application {
    public static Context context;

            @Override
            public void onCreate(){
                super.onCreate();
                context=getApplicationContext();
            }
}
