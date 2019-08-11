package com.example.android.onlinelaundryservice;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by saboor on 10/12/2017.
 */
public class ProductMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<ProductMenuListItem> object;

    public ProductMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view=convertView;
        view=this.context.getLayoutInflater().inflate(this.resourceId,null);

        ImageView img=(ImageView)view.findViewById(R.id.imgdot);
        TextView txtTitle=(TextView)view.findViewById(R.id.productTitleItem);


        ProductMenuListItem productMenuListItem=object.get(position);

        txtTitle.setText(productMenuListItem.title);
        img.setImageResource(R.drawable.dot);
        return view;

    }
}
