package com.example.eventmang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText login_user_email,login_password;
    Button create_account_button,login;
    TextView forgot_password;
    FirebaseAuth reset_password_by_mail;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Create an new account activity
        create_account_button=findViewById(R.id.create_account);

        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

        //Forgot password activity

        forgot_password=findViewById(R.id.text_forgotpassword);
        reset_password_by_mail=FirebaseAuth.getInstance();

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail=new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your email address to receive the reset password link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Firebase authentication

                        String mail=resetMail.getText().toString();
                        reset_password_by_mail.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset link sent to your Email.",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error! Reset link is not sent"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });

                /*passwordResetDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Close dialog box

                    }
                });*/

                passwordResetDialog.create().show();
            }
        });

        //Log in button activity
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        FirebaseUser user=firebaseAuth.getCurrentUser();

        login_user_email=findViewById(R.id.input_email);
        login_password=findViewById(R.id.input_password);
        login=findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateEmail() | !validatePassword()){
                    return;
                }
                validate(login_user_email.getText().toString(),login_password.getText().toString());

                //user profile data popup
                //isUser();




            }
        });

    }

    //Validation and getting values from firebase database to display user information
    /*private void isUser()
    {

        final String userEnteredEmail=login_user_email.getText().toString().trim();
        final String userEnteredPassword=login_password.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");

        Query checkUser=reference.orderByChild("email").equalTo(userEnteredEmail);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String passwordFromDB=dataSnapshot.child(userEnteredEmail).child("password").getValue(String.class);

                if(dataSnapshot.exists()){

                    if(passwordFromDB.equals(userEnteredPassword)){

                        String nameFromDB=dataSnapshot.child(userEnteredEmail).child("name").getValue(String.class);
                        String emailFromDB=dataSnapshot.child(userEnteredEmail).child("email").getValue(String.class);
                        String phoneFromDB=dataSnapshot.child(userEnteredEmail).child("phone").getValue(String.class);
                        String password1FromDB=dataSnapshot.child(userEnteredEmail).child("password").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phone",phoneFromDB);
                        intent.putExtra("password",password1FromDB);

                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }*/


    //Validation for user using firebase Auth

    private void validate(String userEmail,String userPassword){

        progressDialog.setMessage("Loading .............");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,UserProfile.class));
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateEmail() {
        String val = login_user_email.getText().toString().trim();

        if (val.isEmpty()) {
            login_user_email.setError("Field can not be empty ");
            return false;
        }


        else {

            login_user_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val =login_password.getText().toString().trim();

        if (val.isEmpty()) {
            login_password.setError("Field can not be empty ");
            return false;
        }



        else {

            login_password.setError(null);
            return true;
        }
    }

}