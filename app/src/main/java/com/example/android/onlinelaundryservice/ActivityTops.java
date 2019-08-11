package com.example.android.onlinelaundryservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by saboor on 10/19/2017.
 */
    public class ActivityTops extends AppCompatActivity {

    ImageView back;
    Context context;
    public static String topsProduct="";
    public static String toppsProduct="";
    public static String topppsProduct="";
    LinearLayout i1;
    public static SharedPreferences preferences;
    ProductTops productTops;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearProductTop;
    LinearLayout linearProductTopp;
    LinearLayout linearProductToppp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
       // Toast.makeText(G.context,ActivityTops.topsProduct,Toast.LENGTH_SHORT).show();

        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityListProduct.class);
                startActivity(intent);
            }
        });

        linearProductTop =(LinearLayout)findViewById(R.id.linearProductTop);
        linearProductTopp=(LinearLayout)findViewById(R.id.linearProductTopp);
        linearProductToppp=(LinearLayout)findViewById(R.id.linearProductToppp);

        showTopsProduct();
        showToppsProduct();
        showTopppsProduct();
       // for(int i=0;i<4;i++){
         //    createProductTops();
       //  }

       // i1=(LinearLayout)findViewById(R.id.i1);
        preferences= PreferenceManager.getDefaultSharedPreferences(ActivityTops.this);
        final String email=preferences.getString("email","SIGN IN/SIGN UP");
        // Toast.makeText(G.context,email,Toast.LENGTH_SHORT).show();

      //  i1.setOnClickListener(new View.OnClickListener(){
       //     @Override
       //     public void onClick(View v) {
       //         if(email.equals("")){
        //            Toast.makeText(G.context,"Please Sign In.",Toast.LENGTH_SHORT).show();
                    // Intent intent=new Intent(G.context,ActivityUserSignIn.class);
                    // startActivityForResult(intent,0);
               // }else{
                //    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ActivityTops.this);
                 //   View parent=getLayoutInflater().inflate(R.layout.dialog,null);



                //    bottomSheetDialog.setContentView(parent);
                //    BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from((View)parent.getParent());
                 //   bottomSheetBehavior.setPeekHeight(

                //            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400,getResources().getDisplayMetrics())
                //    );
                //    bottomSheetDialog.show();
              //  }
          //  }
      //  });
    }


    public void createProductTops(int id,String title,String price,String picUrl){

        productTops=new ProductTops(G.context);
        productTops.id=id;
        Picasso.with(G.context).load(picUrl).into(ProductTops.pic);
        productTops.title.setText(title);
        productTops.price.setText(price);
       // Picasso.with(G.context).load()
        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductTop.addView(productTops);

    }

    public void createProductTopps(int id,String title,String price,String picUrl){

        productTops=new ProductTops(G.context);
        productTops.id=id;
        Picasso.with(G.context).load(picUrl).into(ProductTops.pic);
        productTops.title.setText(title);
        productTops.price.setText(price);
        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductTopp.addView(productTops);

    }

    public void createProductToppps(int id,String title,String price,String picUrl){

        productTops=new ProductTops(G.context);
        productTops.id=id;
        Picasso.with(G.context).load(picUrl).into(ProductTops.pic);
        productTops.title.setText(title);
        productTops.price.setText(price);
        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductToppp.addView(productTops);

    }

    public void showTopppsProduct() {
        try {
            JSONArray jsonArray = new JSONArray(topppsProduct);

            int i;
            for ( i = 0; i < jsonArray.length(); i++) {


                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String price = String.valueOf(object.getInt("price") + " kr");
                //  String introduction=object.getString("introduction");
                String pic = object.getString("pic");
                String picUrl="http://192.168.1.4/laundry/img/"+pic;



                //Toast.makeText(G.context,title, Toast.LENGTH_LONG).show();
                createProductToppps(id,title, price,picUrl);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    public void showToppsProduct() {
        try {
            JSONArray jsonArray = new JSONArray(toppsProduct);

            int i;
            for ( i = 0; i < jsonArray.length(); i++) {


            JSONObject object = jsonArray.getJSONObject(i);
            int id = object.getInt("id");
            String title = object.getString("title");
            String price = String.valueOf(object.getInt("price") + " kr");
            //  String introduction=object.getString("introduction");
            String pic = object.getString("pic");
            String picUrl="http://192.168.1.4/laundry/img/"+pic;



            //Toast.makeText(G.context,title, Toast.LENGTH_LONG).show();
            createProductTopps(id,title, price,picUrl);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }



    public void showTopsProduct() {
        try {



            JSONArray jsonArray = new JSONArray(topsProduct);

            int i;
            for (i = 0; i < jsonArray.length(); i++) {


            JSONObject object = jsonArray.getJSONObject(i);
            int id = object.getInt("id");
            String title = object.getString("title");
            String price = String.valueOf(object.getInt("price") + " kr");
            //  String introduction=object.getString("introduction");
            String pic = object.getString("pic");
            String picUrl="http://192.168.1.4/laundry/img/"+pic;


            createProductTops(id,title,price,picUrl);
            }

        } catch (JSONException e) {
            e.printStackTrace();

    }


    }





    //  JSONArray hardJson=object.getJSONArray("hard");

    //     for(int k=0;k<hardJson.length();k++){
    //         hardarray.add(hardJson.getString(k));
    //      }
//
    //    for(int k=0;k<hardarray.size();k++){
    //       log.i("LOG",hardarray.get(k));
    //   }


    //   float rating=Float.valueOf(object.getString("rating"));
    //   ratingBar.setRating(rating);


    //  txtMark.setText(rating+"from 5");


    // JSONArray points=object.getJSONArray("point");


    //  for (int j=0;j<points.length();j++){
    //    pointLenght=points.length();
    //     String title2=(String)points.get(j);


    //     CustomPoint customPoint=new CustomPoint(G.context);

    //   }




    }
