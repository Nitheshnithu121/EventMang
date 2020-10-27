package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pie_chart);

        com.github.mikephil.charting.charts.PieChart pieChart=findViewById(R.id.pieChart);

        ArrayList<PieEntry> visitors=new ArrayList<>();
        visitors.add(new PieEntry(50,2016));
        visitors.add(new PieEntry(60,2017));
        visitors.add(new PieEntry(75,2018));
        visitors.add(new PieEntry(60,2019));
        visitors.add(new PieEntry(57,2020));

        PieDataSet pieDataSet=new PieDataSet(visitors,"Pie Chart Representation of Scheduled Events");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(20f);

        PieData pieData=new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Events");
        pieChart.animate();
    }
}