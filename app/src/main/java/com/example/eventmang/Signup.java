package com.example.eventmang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText sign_name, sign_email, sign_phone, sign_password;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //To store data in firebase authentication
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        //Firebase Authentication
        firebaseAuth=FirebaseAuth.getInstance();

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Signup.this,Login.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.back_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent(Signup.this,Login.class);
                startActivity(intent2);
            }
        });

        sign_name=findViewById(R.id.signup_username);
        sign_email=findViewById(R.id.signup_email);
        sign_phone=findViewById(R.id.signup_phone);
        sign_password=findViewById(R.id.signup_password);



        findViewById(R.id.button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //field validation
                if(! validateName() | !validateEmail() | !validatePhone() | !validatePassword()){
                    return;
                }



                //Data base connection and storing data

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name=sign_name.getText().toString();
                String email=sign_email.getText().toString();
                String phone=sign_phone.getText().toString();
                String password=sign_password.getText().toString();

                //To store data in Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email,password);

                Userhelperforsignup userhelperforsignup=new Userhelperforsignup(name,email,phone,password);
                reference.child(name).setValue(userhelperforsignup);
                Toast.makeText(Signup.this,"Continue",Toast.LENGTH_SHORT).show();






                //recaptcha verification
                Intent intent = new Intent(Signup.this, Recaptcha.class);
                startActivity(intent);




            }
        });





    }

    private boolean validateName() {
        String val = sign_name.getText().toString().trim();
        if (val.isEmpty()) {
            sign_name.setError("Field can not be empty ");
            return false;
        }
        else if(val.length()>20)
        {
            sign_name.setError("Name/Username is too large");
            return false;
        }
        else {

            sign_name.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        String val = sign_email.getText().toString().trim();
        String checkemail="[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";


        if (val.isEmpty()) {
            sign_email.setError("Field can not be empty ");
            return false;
        }

        else if(!val.matches(checkemail))
        {
            sign_email.setError("Invalid email");
            return false;
        }

        else {

            sign_email.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {

        String val = sign_phone.getText().toString().trim();

        if (val.isEmpty()) {
            sign_phone.setError("Field can not be empty ");
            return false;
        }

        else if(val.length()!=10)
        {
            sign_phone.setError("Wrong Phone number");
            return false;
        }

        else {

            sign_phone.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = sign_password.getText().toString().trim();

        if (val.isEmpty()) {
            sign_password.setError("Field can not be empty ");
            return false;
        }



        else {

            sign_password.setError(null);
            return true;
        }
    }



}