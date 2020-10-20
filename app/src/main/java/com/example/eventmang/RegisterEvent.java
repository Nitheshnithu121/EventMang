package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterEvent extends AppCompatActivity {

    private Spinner event_spinner,location_spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_event);

        event_spinner=findViewById(R.id.event_spinner);
        location_spinner=findViewById(R.id.location_spinner);

        String[] event_names=getResources().getStringArray(R.array.events);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,event_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event_spinner.setAdapter(adapter);


        String[] location_name=getResources().getStringArray(R.array.location);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,location_name);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(adapter1);

    }
}