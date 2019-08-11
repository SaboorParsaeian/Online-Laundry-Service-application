package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ActivityShow extends AppCompatActivity  {

    public static SharedPreferences preferences;
    SliderLayout sliderShow;
    ArrayList<String> urlPics;
    ArrayList<String> names;
    AppBarLayout appBarLayout;
    ImageView wback;
    LinearLayout back;
    TextView txtProductTitle;
    TextView txtProductExplanation;
    TextView txtProductPrice;
    String email="";
    String id="";


    public static String data="";
    public static String info="";
    public static String basket="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        preferences= PreferenceManager.getDefaultSharedPreferences(ActivityShow.this);
        email=preferences.getString("email","SIGN IN/SIGN UP");

        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");

     //Toast.makeText(G.context,info,Toast.LENGTH_LONG).show();
        back=(LinearLayout)findViewById(R.id.back);
        txtProductTitle=(TextView)findViewById(R.id.txtProductTitle);
        txtProductExplanation=(TextView)findViewById(R.id.txtProductExplanation);
        txtProductPrice=(TextView)findViewById(R.id.txtProductPrice);
        final Button btnAddBasket=(Button)findViewById(R.id.btnAddBasket);

        btnAddBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(G.context,"CLICKED",Toast.LENGTH_LONG).show();
                new AsyncTaskAddToBasket("http://192.168.1.4/laundry/addbasket.php",email,id).execute();



                final Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!ActivityShow.basket.equals("") ){

                                  //  new AsyncTaskReadBasket("http://192.168.1.4/laundry/readbasket.php",email).execute();
                                  //Toast.makeText(G.context,basket,Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(G.context,ActivityWaitBasket.class);
                                    startActivity(intent);
                                    timer.cancel();


                                }

                            }
                        });
                    }
                },1,1000);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(G.context,"salam",Toast.LENGTH_LONG).show();
              //  Intent intent=new Intent(G.context,ActivityTops.class);
              //  startActivity(intent);
            }
        });


        urlPics=new ArrayList<>();
        names=new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(data);

            int i;
            for (i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String pic = object.getString("pic");
                String picUrl="http://192.168.1.4/laundry/img/"+pic;
                urlPics.add(picUrl);

              Log.i("LOG",urlPics.get(i));

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }


        try {



            JSONArray jsonArray = new JSONArray(info);

            int i;
            for (i = 0; i < jsonArray.length(); i++) {


                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                String price = String.valueOf(object.getInt("price") + " kr");
                String introduction = object.getString("intro");

                txtProductTitle.setText(title);
                txtProductExplanation.setText(introduction);
                txtProductPrice.setText(price);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }



        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.sliderShow);

        for (int i=0;i<urlPics.size();i++){

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(urlPics.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderShow.addSlider(textSliderView);
        }

        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_layout);
        wback=(ImageView)findViewById(R.id.wback);




        for (int i=0;i<urlPics.size();i++){

            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView

                    .image(urlPics.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderShow.addSlider(textSliderView);

        }
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                 //Log.i("LOG",verticalOffset+"");

                    int scroll=-(verticalOffset);
                    if(scroll>=619){
                        wback.setImageResource(R.drawable.back);

                    }else{
                        wback.setImageResource(R.drawable.bback);

                    }
                }
            });

    }


}
