package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeDashboard extends AppCompatActivity {

    LinearLayout cs;
    ImageView do_payment,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_dashboard);

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout=new Intent(HomeDashboard.this,Login.class);
                startActivity(logout);
            }
        });

        cs=findViewById(R.id.layoutCs);
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,EventsBoard.class);
                startActivity(intent);
            }
        });



        //Add to google calender
        do_payment=findViewById(R.id.menu_image);
        do_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payment=new Intent(HomeDashboard.this,Payment.class);
                startActivity(payment);

            }
        });
    }
}