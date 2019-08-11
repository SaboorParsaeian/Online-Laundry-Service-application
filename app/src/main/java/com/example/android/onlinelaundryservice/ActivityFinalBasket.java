package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityFinalBasket extends AppCompatActivity {

    LinearLayout linearCustomBasket;
    LinearLayout linearPay;
    public static SharedPreferences preferences;
    LinearLayout.LayoutParams layoutParams;
    TextView txtTotalPrice;
    int totalPrice=0;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        final String email=preferences.getString("email","SIGN IN/SIGN UP");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_basket);

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,MainActivity.class);
                startActivity(intent);
            }
        });

        txtTotalPrice=(TextView)findViewById(R.id.txtTotalPrice);
        linearCustomBasket=(LinearLayout)findViewById(R.id.linearCustomBasket);
        linearPay=(LinearLayout)findViewById(R.id.linearPay);


        //Bundle bundle=getIntent().getExtras();
        //data=bundle.getString("data");

        String data=getIntent().getExtras().getString("data");

        //Toast.makeText(G.context,data,Toast.LENGTH_LONG).show();
        try {
            JSONArray jsonArray = new JSONArray(data);

            int i;
            for ( i = 0; i < jsonArray.length(); i++) {


                JSONObject object = jsonArray.getJSONObject(i);
                int count = object.getInt("count");
                String title = object.getString("title");
                String introduction = object.getString("introduction");
                String price=object.getString("finalprice");
                String pic = object.getString("img");
                String picUrl="http://192.168.1.4/laundry/img/"+pic;
                String idBasket=object.getString("idbasket");
                //Toast.makeText(G.context,title, Toast.LENGTH_LONG).show();

                totalPrice+=Integer.parseInt(price);

                CustomFinalBasket customFinalBasket=new CustomFinalBasket(G.context);
                customFinalBasket.titleBasket.setText(title);
                customFinalBasket.countBasket.setText(count+"");
                customFinalBasket.totalBasket.setText(price+" kr");
                customFinalBasket.idBasket=idBasket;
                customFinalBasket.email=email;
                Picasso.with(G.context).load(picUrl).into(customFinalBasket.imgBasket);
                customFinalBasket.introBasket.setText(introduction);
                layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                linearCustomBasket.addView(customFinalBasket);


            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
        txtTotalPrice.setText(totalPrice+" kr");

        linearPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityPay.class);
                startActivity(intent);
            }
        });

    }
}
