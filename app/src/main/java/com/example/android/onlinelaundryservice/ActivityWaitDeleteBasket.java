package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityWaitDeleteBasket extends AppCompatActivity {
    public static String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_delete_basket);

        String id=getIntent().getExtras().getString("id");
        String email=getIntent().getExtras().getString("email");

        new AsyncTaskDeleteBasket("http://192.168.1.4/laundry/deletebasket.php",id,email).execute();
        new AsyncTaskReadBasket("http://192.168.1.4/laundry/readbasket.php",email).execute();

       // Toast.makeText(G.context,id+ "/"+email, Toast.LENGTH_SHORT).show();

        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!ActivityWaitDeleteBasket.data.equals("") ){
                            Intent intent = new Intent(G.context, ActivityFinalBasket.class);
                            intent.putExtra("data",data);
                            startActivity(intent);
                           // Toast.makeText(G.context,data,Toast.LENGTH_LONG).show();
                            timer.cancel();
                            finish();

                        }

                    }
                });
            }
        },1,1000);

    }
}
