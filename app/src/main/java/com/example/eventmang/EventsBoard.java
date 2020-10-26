package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class EventsBoard extends AppCompatActivity {

    ImageView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_events_board);

        ViewPager viewPager=findViewById(R.id.image_slider);
        ImageAdapter imageAdapter=new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);

        register=findViewById(R.id.register_event_image);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventsBoard.this,RegisterEvent.class);
                startActivity(intent);
            }
        });
    }
}