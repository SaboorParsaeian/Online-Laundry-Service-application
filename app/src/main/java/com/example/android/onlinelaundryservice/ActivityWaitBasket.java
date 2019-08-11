package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saboor on 10/28/2017.
 */
public class ActivityWaitBasket extends AppCompatActivity {
    public static String data="";
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_basket);

       // Bundle bundle=getIntent().getExtras();
       // final String data=bundle.getString("data");


        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        final String email=preferences.getString("email","SIGN IN/SIGN UP");


        if(email.equals("")){

        }else{
            new AsyncTaskReadBasket("http://192.168.1.4/laundry/readbasket.php",email).execute();
        }

            new AsyncTaskShowBasket("http://192.168.1.4/laundry/showbasket.php",email).execute();

        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!ActivityWaitBasket.data.equals("") ){

                            // Toast.makeText(G.context,ActivityFinalBasket.data,Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(ActivityWaitBasket.this, ActivityFinalBasket.class);
                             intent.putExtra("data",data);
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
