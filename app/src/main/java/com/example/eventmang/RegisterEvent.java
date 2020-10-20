package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEvent extends AppCompatActivity {

    private Spinner event_spinner,location_spinner;
    private EditText user_name,user_email,user_phone;
    private Button lock;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



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

        //getting field values
        user_name=findViewById(R.id.signup_username2);
        user_email=findViewById(R.id.signup_email2);
        user_phone=findViewById(R.id.signup_phone2);
        lock=findViewById(R.id.button_lock);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(! validateName() | !validateEmail() | !validatePhone()){
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("events");

                String name=user_name.getText().toString();
                String email=user_email.getText().toString();
                String phone=user_phone.getText().toString();
                String event=event_spinner.getSelectedItem().toString();
                String location=location_spinner.getSelectedItem().toString();

                Userhelperforeventreg userhelperforeventreg=new Userhelperforeventreg(name,email,phone,event,location);
                reference.child(name).setValue(userhelperforeventreg);
                Toast.makeText(RegisterEvent.this,"Event Conformed",Toast.LENGTH_SHORT).show();

            }
        });


    }

    private boolean validateName() {
        String val = user_name.getText().toString().trim();
        if (val.isEmpty()) {
            user_name.setError("Field can not be empty ");
            return false;
        }
        else if(val.length()>20)
        {
            user_name.setError("Name/Username is too large");
            return false;
        }
        else {

            user_name.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        String val = user_email.getText().toString().trim();
        String checkemail="[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";


        if (val.isEmpty()) {
            user_email.setError("Field can not be empty ");
            return false;
        }

        else if(!val.matches(checkemail))
        {
            user_email.setError("Invalid email");
            return false;
        }

        else {

            user_email.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {

        String val = user_phone.getText().toString().trim();

        if (val.isEmpty()) {
            user_phone.setError("Field can not be empty ");
            return false;
        }

        else if(val.length()!=10)
        {
            user_phone.setError("Wrong Phone number");
            return false;
        }

        else {

            user_phone.setError(null);
            return true;
        }
    }
}