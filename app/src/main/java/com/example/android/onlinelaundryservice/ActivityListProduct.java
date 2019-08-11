package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by saboor on 10/18/2017.
 */
public class ActivityListProduct extends AppCompatActivity {


    LinearLayout accessories;
    LinearLayout bedding;
    LinearLayout business;
    LinearLayout dresses;
    LinearLayout homm;
    LinearLayout knitwear;
    LinearLayout laundrys;
    LinearLayout outdoor;
    LinearLayout suits;
    LinearLayout tops;
    LinearLayout trousers;
    LinearLayout skipp;
    ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listproduct);

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,MainActivity.class);
                startActivity(intent);
            }
        });

        accessories=(LinearLayout)findViewById(R.id.accessories);
        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityAccessories.class);
                startActivity(intent);
            }
        });


        bedding=(LinearLayout)findViewById(R.id.bedding);
        bedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityBedding.class);
                startActivity(intent);
            }
        });


        business=(LinearLayout)findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityBusiness.class);
                startActivity(intent);
            }
        });


        dresses=(LinearLayout)findViewById(R.id.dresses);
        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityDresses.class);
                startActivity(intent);
            }
        });


        laundrys=(LinearLayout)findViewById(R.id.laundrys);
        laundrys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityLaundry.class);
                startActivity(intent);
            }
        });



        homm=(LinearLayout)findViewById(R.id.homm);
        homm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityHome.class);
                startActivity(intent);
            }
        });




        knitwear=(LinearLayout)findViewById(R.id.knitwear);
        knitwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityKnitwear.class);
                startActivity(intent);
            }
        });


        outdoor=(LinearLayout)findViewById(R.id.outdoor);
        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityOutdoor.class);
                startActivity(intent);
            }
        });



        suits=(LinearLayout)findViewById(R.id.suits);
        suits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivitySuits.class);
                startActivity(intent);
            }
        });



        tops=(LinearLayout)findViewById(R.id.tops);
        tops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityTops.class);
                startActivity(intent);
            }
        });



        trousers=(LinearLayout)findViewById(R.id.trousers);
        trousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityTrousers.class);
                startActivity(intent);
            }
        });



        skipp=(LinearLayout)findViewById(R.id.skipp);
        skipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityPay.class);
                startActivity(intent);
            }
        });











    }
}
