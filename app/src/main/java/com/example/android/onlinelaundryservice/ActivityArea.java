package com.example.android.onlinelaundryservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saboor on 10/20/2017.
 */
public class ActivityArea extends AppCompatActivity {

    public static String data="";
    EditText edtCode;
    LinearLayout check;
    ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);


        back=(ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,MainActivity.class);
                startActivity(intent);
            }
        });


        edtCode=(EditText)findViewById(R.id.edtCode);

        check =(LinearLayout)findViewById((R.id.check));



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=edtCode.getText().toString();

                if (code.equals("a")){
                    Toast.makeText(G.context,"We cover your postcode!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(G.context,ActivityListProduct.class);
                    startActivity(intent);

                }else if(code.equals("b")){
                    Toast.makeText(G.context,"coverage",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(G.context,ActivityListProduct.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(G.context,"Sorry, we don't cover your postcode yet! we are always expanding so please check back again soon.",Toast.LENGTH_SHORT).show();
                }













            }


    });
    }}

