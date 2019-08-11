package com.example.android.onlinelaundryservice;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by saboor on 10/28/2017.
 */
public class CustomFinalBasket extends LinearLayout {
    public TextView titleBasket;
    public String idBasket="";
    public String email="";
    public ImageView imgBasket;
    public TextView countBasket;
    public TextView introBasket;
    public TextView totalBasket;
    public TextView deleteBasket;
  //  LinearLayout linearLayout;
  //  public String data;

    public CustomFinalBasket(Context context) {
        super(context);
        init(context);
    }
    public CustomFinalBasket(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public CustomFinalBasket(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_final_basket,this,true);

        titleBasket=(TextView)view.findViewById(R.id.titleCustomFinalBasket);
        imgBasket=(ImageView) view.findViewById(R.id.imgCustomFinalBasket);
        countBasket=(TextView)view.findViewById(R.id.countCustomFinalBasket);
        introBasket=(TextView)view.findViewById(R.id.introCustomFinalBasket);
        totalBasket=(TextView)view.findViewById(R.id.totalCustomFinalBasket);
        deleteBasket=(TextView)view.findViewById(R.id.deleteCustomFinalBasket);
        deleteBasket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(G.context,ActivityWaitDeleteBasket.class);
                intent.putExtra("id",idBasket);
                intent.putExtra("email",email);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                G.context.startActivity(intent);

            }
        });


    }
}
