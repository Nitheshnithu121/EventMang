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
        visitors.add(new PieEntry(508,2016));
        visitors.add(new PieEntry(600,2017));
        visitors.add(new PieEntry(750,2018));
        visitors.add(new PieEntry(600,2019));
        visitors.add(new PieEntry(670,2020));

        PieDataSet pieDataSet=new PieDataSet(visitors,"Pie Chart Representation of Events");
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