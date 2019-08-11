package com.example.android.onlinelaundryservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityUserSignIn extends AppCompatActivity {

    CheckBox showPass;
    EditText edtEmail;
    EditText edtPass;
    TextView signup;
    public static String data="";
    LinearLayout btnGo;
    SharedPreferences preferences;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String insertDone=bundle.getString("do");

            preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("do",insertDone);
            editor.commit();
            Intent intent=new Intent(G.context,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.user_sign);
        super.onCreate(savedInstanceState);



        showPass=(CheckBox)findViewById(R.id.showPass);
        btnGo=(LinearLayout)findViewById(R.id.btnGo);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPass=(EditText)findViewById(R.id.edtPass);
        signup=(TextView)findViewById(R.id.signup);





        btnGo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String email=edtEmail.getText().toString();
                String pass=edtPass.getText().toString();

                new AsyncTaskConnect("http://192.168.1.4/laundry/",email,pass).execute();
                new AsyncTaskReadBasket("http://192.168.1.4/laundry/readbasket.php",email).execute();




                final ProgressDialog dialog=new ProgressDialog(ActivityUserSignIn.this);
                dialog.setMessage("Please Wait...");
                dialog.show();


                final Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!data.equals("")){
                                    dialog.cancel();


                                    if (data.equals("not exist")){
                                        Toast.makeText(G.context,"Oops, please check your email address and password.",Toast.LENGTH_LONG).show();
                                        timer.cancel();
                                    }else {
                                    Intent intent=new Intent(G.context,MainActivity.class);
                                    intent.putExtra("email",data);
                                    setResult(RESULT_OK,intent);
                                    timer.cancel();
                                    finish();}
                                }

                            }
                        });
                    }
                },1,1000);

            }
        });


        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showPass.isChecked()){

                    Toast.makeText(G.context,"checked",Toast.LENGTH_LONG).show();
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                else  {

                    edtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                }
            }
    });

        signup.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent=new Intent(G.context,ActivityUserSignUp.class);
                                          startActivity(intent);

                                      }
                                  }
        );
}
}