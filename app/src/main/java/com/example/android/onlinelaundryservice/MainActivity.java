package com.example.android.onlinelaundryservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    LinearLayout linearFinalBasket;
    LinearLayout howWork;
    ImageView imgsearch;
    LinearLayout drycleaning;
    LinearLayout userEmail;
    public static SharedPreferences preferences;
    LinearLayout area;
    LinearLayout linearShowAllProduct;
    SliderLayout sliderShow;
    ArrayList<String> urlPics;
    public static String basket="";


   // String[] menues={"CATEGORIES","AMAZING SUGGESTION","FAQ","CONTACT","ABOUT US"};

    ArrayList<BuyMenuListItem>items;
    ArrayList<ProductMenuListItem>productItems;
    ArrayList<SettingMenuListItem>settingItem;

    ImageView imgmenu;
    String drawerMenuList;
    DrawerLayout drawerLayout;
    ListView navigateListview;
    ListView productList;
    ListView settingList;

    TextView txtsign;
    TextView txtBasketCont;
    LinearLayout txtlogOut;
    LinearLayout search;
    TextView order;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String email=bundle.getString("email");
            txtsign.setText(email);
            preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("email",email);
            editor.commit();



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        txtBasketCont=(TextView)findViewById(R.id.txtBasketCont);

       //Toast.makeText(G.context,topsProduct,Toast.LENGTH_SHORT).show();

      //  Log.i("LOG",topsProduct);


        if(basket.equals("not exist")) {
        txtBasketCont.setVisibility(View.GONE);
        }else {
            txtBasketCont.setText(basket);
        }




        urlPics=new ArrayList<>();
        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider);
        imgmenu=(ImageView)findViewById(R.id.imgmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigateListview=(ListView)findViewById(R.id.navigateListview);
        userEmail=(LinearLayout)findViewById(R.id.userEmail);
        productList=(ListView)findViewById(R.id.productListView);
        settingList=(ListView)findViewById(R.id.settingListView);
        txtlogOut=(LinearLayout)findViewById(R.id.logOut);
        linearFinalBasket=(LinearLayout)findViewById(R.id.linearFinalBasket);



        linearFinalBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityWaitBasket.class);
                startActivity(intent);

            }
        });

        items=new ArrayList<>();
        productItems=new ArrayList<>();
        settingItem=new ArrayList<>();

        items.add(new BuyMenuListItem(R.drawable.menu3,"Order now"));

        productItems.add(new ProductMenuListItem(R.drawable.dot,"Amazing Suggestion"));
        productItems.add(new ProductMenuListItem(R.drawable.dot,"FAQs"));
        productItems.add(new ProductMenuListItem(R.drawable.dot,"CONTACT US"));
        productItems.add(new ProductMenuListItem(R.drawable.dot,"ABOUT US"));

        settingItem.add(new SettingMenuListItem(R.drawable.setting,"Settings"));

        navigateListview.setAdapter(new BuyMenuAdapter(MainActivity.this,R.layout.buy_menu_list,items));
        productList.setAdapter(new ProductMenuAdapter(MainActivity.this,R.layout.product_menu_list,productItems));
        settingList.setAdapter(new SettingMenuAdapter(MainActivity.this,R.layout.setting_menu_list,settingItem));



        navigateListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position )
                {
                    case 0:  Intent newActivity = new Intent(G.context, ActivityListProduct.class);
                        startActivity(newActivity);
                        break;

                }
            }
            });


        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position )
                {
                    case 0:  Intent newActivity = new Intent(G.context, ActivityAmazingSuggestion.class);
                        startActivity(newActivity);
                        break;
                    case 1:  Intent newActivity1 = new Intent(G.context, Activity_FAQs.class);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(G.context, ActivityContactUs.class);
                        startActivity(newActivity2);
                        break;
                    case 3:  Intent newActivity3 = new Intent(G.context, ActivityAboutUs.class);
                        startActivity(newActivity3);
                        break;

                }
            }
        });

        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch( position )
                {
                    case 0:  Intent newActivity = new Intent(G.context, SettingsActivity.class);
                        startActivity(newActivity);
                        break;

                }

            }
        });







        txtsign=(TextView)findViewById(R.id.txtsign);
        search=(LinearLayout)findViewById(R.id.search);

        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        String email=preferences.getString("email","SIGN IN/SIGN UP");






       if(email.equals("")){
           txtsign.setText("SIGN IN/SIGN UP");
       }else {
           txtsign.setText(email);
       }






        txtsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtsign.getText().toString().equals("SIGN IN/SIGN UP")){

                    Intent intent=new Intent(G.context,ActivityUserSignIn.class);
                    startActivityForResult(intent,0);
                }else {

                    if(userEmail.getVisibility()==View.VISIBLE){
                        userEmail.setVisibility(View.GONE);
                    }else{
                        userEmail.setVisibility(View.VISIBLE);
                    }


                }




            }
        });

        txtlogOut.setOnClickListener(new View.OnClickListener(){
                                         @Override
                                         public void onClick(View v) {
                                             preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
                                             SharedPreferences.Editor editor=preferences.edit();
                                             editor.putString("email","");
                                             editor.putString("do","");
                                             editor.commit();
                                             drawerLayout.closeDrawer(Gravity.LEFT);


                                         }
                                     }

        );

       // order.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
         //       preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
         //       SharedPreferences.Editor editor=preferences.edit();
         //   }
      //  });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,ActivityArea.class);
                startActivity(intent);
            }
        });

        imgmenu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        txtsign=(TextView)findViewById(R.id.txtsign);

        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        String email=preferences.getString("email","SIGN IN/SIGN UP");

        String insertDone=preferences.getString("do","");


        if(email.equals("")){

            if(insertDone.equals("")){
                txtsign.setText("SIGN IN/SIGN UP");

            }else {
                txtsign.setText(insertDone);
            }



        }else {
            txtsign.setText(email);
        }
        userEmail.setVisibility(View.GONE);
        drawerLayout.openDrawer(Gravity.LEFT);

    }
});

        urlPics.add("http://www.washermen.com/img/picture4.jpg");
        urlPics.add("http://abdrycleaners.com/images/banner-4.jpg");
        urlPics.add("https://www.laundrywaves.com/images/wash-fold.png");
        urlPics.add("http://www.laundryservices.com.sg/images/why-hire-us-as-your-home-delivery-laundry-service.jpg");


        for (int i=0;i<urlPics.size();i++){

            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView

                    .image(urlPics.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderShow.addSlider(textSliderView);

        }

        linearShowAllProduct=(LinearLayout)findViewById(R.id.linearShowAllProduct);
        linearShowAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(G.context,"clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(G.context,ActivityListProduct.class);
                startActivity(intent);
            }
        });

        howWork=(LinearLayout)findViewById(R.id.washIron);
        howWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(G.context,"clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(G.context,HowWork.class);
                startActivity(intent);
            }
        });



        imgsearch=(ImageView)findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(G.context,"clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(G.context,ActivityArea.class);
                startActivity(intent);
            }
        });



        area=(LinearLayout)findViewById(R.id.area);
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(G.context,"clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(G.context,ActivityArea.class);
                startActivity(intent);
            }
        });





    }
}
