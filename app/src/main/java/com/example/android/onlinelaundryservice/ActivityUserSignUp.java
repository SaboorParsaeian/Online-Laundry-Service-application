package com.example.android.onlinelaundryservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saboor on 10/14/2017.
 */
public class ActivityUserSignUp extends AppCompatActivity {
    public static String data="";
    EditText edtUserName;
    EditText edtEmail;
    EditText edtPass;
    EditText edtRePass;
    EditText edtAddress;
    EditText edtPostCode;
    EditText edtPhone;
    LinearLayout linearSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtUserName=(EditText)findViewById(R.id.edtUserName);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPass=(EditText)findViewById(R.id.edtPass);
        edtRePass=(EditText)findViewById(R.id.edtRePass);
        edtAddress=(EditText)findViewById(R.id.edtAddress);
        edtPostCode=(EditText)findViewById(R.id.edtPostCode);
        edtPhone=(EditText)findViewById(R.id.edtPhone);
        linearSignUp =(LinearLayout)findViewById((R.id.linearSignUp));



        linearSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=edtUserName.getText().toString();
                final String email=edtEmail.getText().toString();
                final String pass=edtPass.getText().toString();
                final String repass=edtRePass.getText().toString();
                final String address=edtAddress.getText().toString();
                final String postcode=edtPostCode.getText().toString();
                final String phone=edtPhone.getText().toString();
                final String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";
                Matcher matcherObj = Pattern.compile(validemail).matcher(email);
                final String validpass= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+\\@]{6,256}";
                Matcher matcherObject = Pattern.compile(validpass).matcher(pass);




                if(username.equals("")){
                    Toast.makeText(G.context, "Please enter your username.", Toast.LENGTH_LONG).show();
                }else if(email.equals("")){
                    Toast.makeText(G.context, "Please enter your email.", Toast.LENGTH_LONG).show();
                } else if (!matcherObj.matches()){
                    Toast.makeText(getApplicationContext(),"Please enter a valid email address.",Toast.LENGTH_LONG).show();
                } else if(pass.equals("")){
                    Toast.makeText(G.context, "Please enter your password.", Toast.LENGTH_LONG).show();
                } else if (!matcherObject.matches()){
                    Toast.makeText(getApplicationContext(),"Please enter a password with at least 6 characters.",Toast.LENGTH_LONG).show();
                }else if(address.equals("")){
                    Toast.makeText(G.context, "Please enter your address.", Toast.LENGTH_LONG).show();
                }else if(phone.equals("")){
                    Toast.makeText(G.context, "Please enter your phone number.", Toast.LENGTH_LONG).show();
                } else {

                    if (pass.equals(repass)) {

                        new AsyncTaskInsertUser("http://192.168.1.4/laundry/insertuser.php",username,email,pass,address,postcode,phone).execute();

                        final ProgressDialog dialog = new ProgressDialog(ActivityUserSignUp.this);
                        dialog.setMessage("Please Wait...");
                        dialog.show();


                        final Timer timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!data.equals("")) {
                                            dialog.cancel();
                                            // Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();


                                            if (data.equals("not ok")) {
                                                Toast.makeText(G.context, "Sorry, We couldn't register you. Please confirm you have an internet connection and try again in a moment or contact us for help.", Toast.LENGTH_LONG).show();
                                                timer.cancel();

                                            } else if (data.equals("exist")) {
                                                Toast.makeText(G.context, "This email is taken by another account.", Toast.LENGTH_SHORT).show();
                                                timer.cancel();
                                            } else {

                                                Intent intent = new Intent(G.context, ActivityUserSignIn.class);
                                                intent.putExtra("do", data);
                                                setResult(RESULT_OK, intent);
                                                timer.cancel();
                                                finish();
                                            }
                                        }
                                    }


                                });

                            }


                        }, 1, 1000);


                    } else {
                        Toast.makeText(G.context, "Please Repeat Your Password Correctly", Toast.LENGTH_SHORT).show();
                    }
                }      }

        });
    }
            }
