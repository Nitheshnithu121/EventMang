package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    ImageView back;
    TextView share,rate_us;
    String email,passwd;
    EditText name,password;
    TextView profile_email,graph;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        //Graph view

        graph=findViewById(R.id.profile_graph);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,Graph.class);
                startActivity(intent);
            }
        });


        //profile details
        name=findViewById(R.id.profile_name);
        profile_email=findViewById(R.id.user_profile_email);
        password=findViewById(R.id.profile_phone);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        passwd=intent.getStringExtra("password");
        name.setText(email);
        password.setText(passwd);
        profile_email.setText(email);



        back=findViewById(R.id.back_image3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,HomeDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        //Share application option
        share=findViewById(R.id.profile_share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareintent=new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT,"Please share this application");
                shareintent.setType("text/plain");
                startActivity(Intent.createChooser(shareintent,"Share via"));

            }
        });

        //Rate us option
        rate_us=findViewById(R.id.profile_rate);
        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playstore=new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/"));
                startActivity(playstore);
            }
        });











    }

}