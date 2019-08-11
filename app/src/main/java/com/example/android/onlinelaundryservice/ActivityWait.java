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

/**
 * Created by saboor on 10/25/2017.
 */
public class ActivityWait extends AppCompatActivity {
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("id");
        final String id2=String.valueOf(id);

        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        final String email=preferences.getString("email","SIGN IN/SIGN UP");
        if(email.equals("")){
            Toast.makeText(G.context,"Please Sign In.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(G.context, MainActivity.class);
            startActivityForResult(intent,1);

             }

        //  Toast.makeText(G.context,id+"", Toast.LENGTH_SHORT).show();

        new AsyncTaskDetail("http://192.168.1.4/laundry/getdetail.php",id2).execute();
        new AsyncTaskGetProductInfo("http://192.168.1.4/laundry/getproductinfo.php",id2).execute();



    final Timer timer=new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!ActivityShow.data.equals("") & !ActivityShow.info.equals("") & !email.equals("") ){

                        // Toast.makeText(G.context,ActivityTops.topsProduct,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(G.context, ActivityShow.class);
                        intent.putExtra("id",id2);
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
