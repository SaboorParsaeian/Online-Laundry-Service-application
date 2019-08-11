package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by saboor on 10/30/2017.
 */
public class AsyncTaskPlaceOrder extends AsyncTask {

    public String link="";
    public String email;
    public String description;
    public String date;
    public String time;

    public AsyncTaskPlaceOrder(String link,String email,String description,String date,String time){
        this.link=link;
        this.email=email;
        this.description=description;
        this.date=date;
        this.time=time;


    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {

            String data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(email,"UTF8");
            data+="&"+URLEncoder.encode("description","UTF8")+"="+URLEncoder.encode(description,"UTF8");
            data+="&"+URLEncoder.encode("date","UTF8")+"="+URLEncoder.encode(date,"UTF8");
            data+="&"+URLEncoder.encode("time","UTF8")+"="+URLEncoder.encode(time,"UTF8");

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
            ActivityPay.data=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}
