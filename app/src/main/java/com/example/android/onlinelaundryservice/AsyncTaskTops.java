package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by saboor on 10/22/2017.
 */
public class AsyncTaskTops extends AsyncTask {
    public String link="";


    public AsyncTaskTops(String link){
        this.link=link;



    }



    @Override
    protected Object doInBackground(Object[] params) {

        try {


            URL url=new URL(link);
            URLConnection connection=url.openConnection();




            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder=new StringBuilder();

            String line=null;


            while ((line=reader.readLine())!=null){

                builder.append(line);
            }
            ActivityTops.topsProduct=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}
