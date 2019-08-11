package com.example.android.onlinelaundryservice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityPay extends AppCompatActivity {

    Button btnDatePicker,btnTimePicker;
    EditText txtDate,txtTime,edtDescription;
    int mYear, mMonth, mDay, mHour, mMin;
    TextView placeOrder;
    ImageView back;
    public static String data="";
    public static SharedPreferences preferences;
    String email="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);




        back=(ImageView)findViewById(R.id.back);
        placeOrder=(TextView) findViewById(R.id.placeOrder);
        edtDescription=(EditText)findViewById(R.id.edtDescription);
        btnDatePicker=(Button)findViewById(R.id.btnDate);
        btnTimePicker=(Button)findViewById(R.id.btnTime);
        txtDate=(EditText)findViewById(R.id.inDate);
        txtTime=(EditText)findViewById(R.id.inTime);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,MainActivity.class);
                startActivity(intent);
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePic();
            }
        });


        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePic();
            }
        });

        preferences= PreferenceManager.getDefaultSharedPreferences(ActivityPay.this);
        email=preferences.getString("email","SIGN IN/SIGN UP");



        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String description=edtDescription.getText().toString();
                final String date=txtDate.getText().toString();
                final String time=txtTime.getText().toString();


                new AsyncTaskPlaceOrder("http://192.168.1.4/laundry/placeorder.php",email,description,date,time).execute();



                //  new AsyncTaskPlaceOrder("http://192.168.1.4/laundry/placeorder.php",email).execute();

                final Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!data.equals("") ){

                                    Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivityPay.this, ActivityFinalBasket.class);
                                    intent.putExtra("data",data);
                                    startActivity(intent);
                                    timer.cancel();
                                    finish();

                                }

                            }
                        });
                    }
                },1,1000);






            }



        });

    }


    public void datePic(){
        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(G.context,new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtDate.setText(dayOfMonth+"/"+monthOfYear+1+"/"+year);
            }
        },mDay,mMonth,mYear);

        datePickerDialog.show();

    }

    public void timePic(){
        final Calendar c=Calendar.getInstance();
        mHour=c.get(Calendar.HOUR_OF_DAY);
        mMin=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(G.context,new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtTime.setText(hourOfDay + ":" + minute);
            }
        },mHour,mMin,false);

        timePickerDialog.show();

    }

}
