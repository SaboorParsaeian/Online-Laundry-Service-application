package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;

/**
 * Created by saboor on 10/14/2017.
 */
public class AsyncTaskConnect extends AsyncTask {


    public String link="";
    public String email;
    public String pass;

    public AsyncTaskConnect(String link,String email,String pass){
        this.link=link;
        this.email=email;
        this.pass=pass;


    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {

            String data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(email,"UTF8");
            data+="&"+URLEncoder.encode("pass","UTF8")+"="+URLEncoder.encode(pass,"UTF8");
            
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
            ActivityUserSignIn.data=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}
