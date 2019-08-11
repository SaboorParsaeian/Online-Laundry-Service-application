package com.example.android.onlinelaundryservice;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by saboor on 10/22/2017.
 */
public class ProductTops extends LinearLayout {

    LinearLayout linearLayout;
    public int id;
    public static ImageView pic;
    public static TextView title;
  //  public static TextView introduction;
    public static TextView price;

    public ProductTops(Context context) {
        super(context);
        init(context);
    }
    public ProductTops(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductTops(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.product_tops,this,true);

        pic=(ImageView)view.findViewById(R.id.imgPic);
        title=(TextView)view.findViewById(R.id.txtTitle);
        price=(TextView)view.findViewById(R.id.txtPrice);
        linearLayout=(LinearLayout)view.findViewById(R.id.topLinear);

        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(G.context,id+"",Toast.LENGTH_SHORT).show();
              Intent intent=new Intent(G.context,ActivityWait.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("id",id);
              G.context.startActivity(intent);

            }
        });

    }
}
