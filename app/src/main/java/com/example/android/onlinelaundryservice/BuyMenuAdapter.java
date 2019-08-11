package com.example.android.onlinelaundryservice;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class BuyMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<BuyMenuListItem>object;

    public BuyMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view=convertView;
        view=this.context.getLayoutInflater().inflate(this.resourceId,null);

        ImageView img=(ImageView)view.findViewById(R.id.imgMenuList);
        TextView txtTitle=(TextView)view.findViewById(R.id.txtTitleMenuList);

        BuyMenuListItem buyMenuListItem=object.get(position);

        txtTitle.setText(buyMenuListItem.title);
        img.setImageResource(R.drawable.menu3);
        return view;

    }
}
