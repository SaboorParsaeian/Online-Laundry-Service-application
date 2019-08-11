package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by saboor on 10/27/2017.
 */
public class AsyncTaskAddToBasket extends AsyncTask {
    public String link="";
    public String email;
    public String id;

    public AsyncTaskAddToBasket(String link,String email,String id){
        this.link=link;
        this.email=email;
        this.id=id;


    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {

            String data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(email,"UTF8");
            data+="&"+URLEncoder.encode("id","UTF8")+"="+URLEncoder.encode(id,"UTF8");

            URL url=new URL(link);
            URLConnection connection=url.openConnection();

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();


            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder=new StringBuilder();

            String line=null;


            while ((line=reader.readLine())!=null){

                builder.append(line);
            }
            ActivityShow.basket=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}
