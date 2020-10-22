package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    EditText user_name,upi_id,amount;
    Button pay_here;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment);


        //Payment option

        user_name=findViewById(R.id.input_name);
        upi_id=findViewById(R.id.input_upi_name);
        amount=findViewById(R.id.input_amount);
        pay_here=findViewById(R.id.payment_here_button);

        pay_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! validateName() | !validateUpiId() | !validateAmount() ){
                    return;
                }
                //Directing to google pay

                String u_name=user_name.getText().toString().trim();
                String u_id=upi_id.getText().toString().trim();
                String u_amount=amount.getText().toString().trim();

                payUsingUpi(u_name,u_id,u_amount);



            }
        });



    }

    void payUsingUpi(String name,String upi_id,String amount){
        String GOOGLE_PAY_PACKAGE_NAME="com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE=123;

        Uri uri=new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa",upi_id)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("am",amount)
                .build();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        startActivityForResult(intent,GOOGLE_PAY_REQUEST_CODE);




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


    private boolean validateUpiId() {
        String val = upi_id.getText().toString().trim();


        if (val.isEmpty()) {
            upi_id.setError("Field can not be empty ");
            return false;
        }


        else {

            upi_id.setError(null);
            return true;
        }
    }

    private boolean validateAmount() {

        String val = amount.getText().toString().trim();

        if (val.isEmpty()) {
            amount.setError("Field can not be empty ");
            return false;
        }


        else {

            amount.setError(null);
            return true;
        }
    }
}