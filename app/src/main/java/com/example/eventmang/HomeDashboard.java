package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeDashboard extends AppCompatActivity {

    LinearLayout cs;
    ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_dashboard);

        cs=findViewById(R.id.layoutCs);
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,EventDetails.class);
                startActivity(intent);
            }
        });

        //user profile

        profile=findViewById(R.id.menu_image);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(HomeDashboard.this,UserProfile.class);
                startActivity(intent2);
            }
        });
    }
}