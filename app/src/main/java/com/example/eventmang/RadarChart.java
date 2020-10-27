package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radar_chart);

        com.github.mikephil.charting.charts.RadarChart radarChart=findViewById(R.id.radarChart);

        ArrayList<RadarEntry> visitorsForFirstWebsite=new ArrayList<>();
        visitorsForFirstWebsite.add(new RadarEntry(420));
        visitorsForFirstWebsite.add(new RadarEntry(490));
        visitorsForFirstWebsite.add(new RadarEntry(520));
        visitorsForFirstWebsite.add(new RadarEntry(500));
        visitorsForFirstWebsite.add(new RadarEntry(460));
        visitorsForFirstWebsite.add(new RadarEntry(450));
        visitorsForFirstWebsite.add(new RadarEntry(540));
        visitorsForFirstWebsite.add(new RadarEntry(660));

        RadarDataSet radarDataSetForFirstWebsite=new RadarDataSet(visitorsForFirstWebsite,"Code Hunt");
        radarDataSetForFirstWebsite.setColor(Color.RED);
        radarDataSetForFirstWebsite.setLineWidth(2f);
        radarDataSetForFirstWebsite.setValueTextColor(Color.RED);
        radarDataSetForFirstWebsite.setValueTextSize(14f);

        //Website 2

        ArrayList<RadarEntry> visitorsForSecondWebsite=new ArrayList<>();
        visitorsForSecondWebsite.add(new RadarEntry(490));
        visitorsForSecondWebsite.add(new RadarEntry(430));
        visitorsForSecondWebsite.add(new RadarEntry(620));
        visitorsForSecondWebsite.add(new RadarEntry(560));
        visitorsForSecondWebsite.add(new RadarEntry(460));
        visitorsForSecondWebsite.add(new RadarEntry(550));
        visitorsForSecondWebsite.add(new RadarEntry(440));
        visitorsForSecondWebsite.add(new RadarEntry(660));

        RadarDataSet radarDataSetForSecondWebsite=new RadarDataSet(visitorsForSecondWebsite,"Data Transfer");
        radarDataSetForSecondWebsite.setColor(Color.BLUE);
        radarDataSetForSecondWebsite.setLineWidth(2f);
        radarDataSetForSecondWebsite.setValueTextColor(Color.BLUE);
        radarDataSetForSecondWebsite.setValueTextSize(14f);



        RadarData radarData=new RadarData();
        radarData.addDataSet(radarDataSetForFirstWebsite);
        radarData.addDataSet(radarDataSetForSecondWebsite);

        String[] labels={"2014","2015","2016","2017","2018","2019","2020"};

        XAxis xAxis=radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.getDescription().setText("Radar Chart Representation of Users attended the events");
        radarChart.setData(radarData);


    }
}