package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class ActivitySplash extends AppCompatActivity {
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new AsyncTaskTops("http://192.168.1.4/laundry/readtops.php").execute();
        new AsyncTaskTopps("http://192.168.1.4/laundry/toppproduct.php").execute();
        new AsyncTaskToppps("http://192.168.1.4/laundry/topppproduct.php").execute();

        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        final String email=preferences.getString("email","SIGN IN/SIGN UP");



        if(email.equals("")){

        }else{
            new AsyncTaskReadBasket("http://192.168.1.4/laundry/readbasket.php",email).execute();
        }


        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      if (!ActivityTops.topsProduct.equals("") & !ActivityTops.toppsProduct.equals("") &
                              !ActivityTops.topppsProduct.equals("") ){

                         // Toast.makeText(G.context,ActivityTops.topsProduct,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(G.context, MainActivity.class);
                        startActivity(intent);
                        timer.cancel();
                        finish();

                  }

                    }
                });
            }
        },1,1000);
    }
}
