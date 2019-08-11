package com.example.android.onlinelaundryservice;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saboor on 10/12/2017.
 */
public class SettingMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<SettingMenuListItem> object;

    public SettingMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view=convertView;
        view=this.context.getLayoutInflater().inflate(this.resourceId,null);

        ImageView img=(ImageView)view.findViewById(R.id.imgsettig);
        TextView txtTitle=(TextView)view.findViewById(R.id.settingTitleItem);


        SettingMenuListItem settingMenuListItem=object.get(position);

        txtTitle.setText(settingMenuListItem.title);
        img.setImageResource(R.drawable.setting);
        return view;

    }
}
