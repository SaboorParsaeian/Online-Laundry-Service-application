package com.example.android.onlinelaundryservice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by saboor on 10/17/2017.
 */
public class AsyncTaskInsertUser extends AsyncTask {

    public String link="";
    public String username;
    public String email;
    public String pass;
    public String address;
    public String postcode;
    public String phone;

    public AsyncTaskInsertUser(String link, String username,String email,String pass,String address,String postcode,String phone){
        this.link=link;
        this.username= username;
        this.email=email;
        this.pass=pass;
        this.address=address;
        this.postcode=postcode;
        this.phone=phone;


    }



    @Override
    protected Object doInBackground(Object[] params) {

        try {

            String data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(email,"UTF8");
            data+="&"+URLEncoder.encode("pass","UTF8")+"="+URLEncoder.encode(pass,"UTF8");
            data+="&"+URLEncoder.encode("username","UTF8")+"="+URLEncoder.encode(username,"UTF8");
            data+="&"+URLEncoder.encode("address","UTF8")+"="+URLEncoder.encode(address,"UTF8");
            data+="&"+URLEncoder.encode("postcode","UTF8")+"="+URLEncoder.encode(postcode,"UTF8");
            data+="&"+URLEncoder.encode("phone","UTF8")+"="+URLEncoder.encode(phone,"UTF8");

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
            ActivityUserSignUp.data=builder.toString();

        }catch (Exception e){


        }
        return "";

    }
}


