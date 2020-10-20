package com.example.eventmang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Recaptcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    TextView web_view;
    Button conform_register;

    CheckBox recaptcha_checkbox;
    GoogleApiClient googleApiClient;

    String site_key="6LfLqtcZAAAAAPxfVCeEKVvXfYL0hJcKfoH2qpqp";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recaptcha);


        //Link to website
        web_view=findViewById(R.id.vist_website);
        web_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent website=new Intent("android.intent.action.VIEW", Uri.parse("https://www.oneinc.com/"));
                startActivity(website);

            }
        });


        //Recaptcha validation

        recaptcha_checkbox=findViewById(R.id.checkBox);
        //Creating google api client
        googleApiClient=new GoogleApiClient.Builder(this).addApi(SafetyNet.API).addConnectionCallbacks(Recaptcha.this).build();
        googleApiClient.connect();

        recaptcha_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(recaptcha_checkbox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,site_key).setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        @Override
                        public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                            Status status=recaptchaTokenResult.getStatus();
                            if((status != null) && status.isSuccess()){
                                Toast.makeText(Recaptcha.this,"Registration Success",Toast.LENGTH_SHORT).show();
                                recaptcha_checkbox.setTextColor(Color.GREEN);
                            }
                        }
                    });
                }else{
                    recaptcha_checkbox.setTextColor(Color.BLACK);
                }
            }
        });

        conform_register=findViewById(R.id.conform);

        conform_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(Recaptcha.this,Login.class);
                startActivity(login);
                Toast.makeText(Recaptcha.this,"Registration Successful",Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}