package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by saboor on 10/23/2017.
 */
public class AsyncTaskTopps extends AsyncTask {
    public String link="";


    public AsyncTaskTopps(String link){
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
            ActivityTops.toppsProduct=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}
