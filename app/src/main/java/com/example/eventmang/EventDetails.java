package com.example.eventmang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

import static com.example.eventmang.R.id.menuSearch;

public class EventDetails extends AppCompatActivity {
    ImageView register_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_event_details);

        //For menu click

        //SmoothBottomBar smoothBottomBar=findViewById(R.id.bottom_bar);




        //Animation view of events
        register_event=findViewById(R.id.register_event_image);

        register_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventDetails.this,RegisterEvent.class);
                startActivity(intent);
            }
        });



        ViewPager2 locationsViewPager=findViewById(R.id.location_view_pager);

        List<EventLocation> eventLocations=new ArrayList<>();

        EventLocation eventLocationBangalore=new EventLocation();
        eventLocationBangalore.imageUrl=(R.drawable.dashboardimg1);
        eventLocationBangalore.title="R.V. College";
        eventLocationBangalore.location="Bangalore";
        eventLocationBangalore.starRating=4.8f;
        eventLocations.add(eventLocationBangalore);

        EventLocation eventLocationMangalore=new EventLocation();
        eventLocationMangalore.imageUrl=(R.drawable.dashboardimg4);
        eventLocationMangalore.title="SJEC";
        eventLocationMangalore.location="Mangalore";
        eventLocationMangalore.starRating=5.0f;
        eventLocations.add(eventLocationMangalore);

        EventLocation eventLocationMoodbidri=new EventLocation();
        eventLocationMoodbidri.imageUrl=(R.drawable.dashboardimg3);
        eventLocationMoodbidri.title="MITE";
        eventLocationMoodbidri.location="Moodbidri";
        eventLocationMoodbidri.starRating=4.9f;
        eventLocations.add(eventLocationMoodbidri);

        locationsViewPager.setAdapter(new EventLocationAdapter(eventLocations));

        locationsViewPager.setClipToPadding(false);
        locationsViewPager.setClipChildren(false);
        locationsViewPager.setOffscreenPageLimit(3);
        locationsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });

        locationsViewPager.setPageTransformer(compositePageTransformer);



    }
}