package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.models.Information;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    TextView semail,spassword,sname,sphno;
    FirebaseAuth sauth;
    Button signupbtn;

    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db=FirebaseDatabase.getInstance().getReference().child("info");

       signupbtn=findViewById(R.id.signupbtn);
sname=findViewById(R.id.sname);
sphno=findViewById(R.id.sphno);
        spassword=findViewById(R.id.spassword);
        semail=findViewById(R.id.semail);

        sauth=FirebaseAuth.getInstance();


       signupbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String s_password=spassword.getText().toString();
                String s_email=semail.getText().toString();
                String s_name=sname.getText().toString();


                String s_phno=sphno.getText().toString();

                if(s_email.isEmpty() && s_password.isEmpty())
                {

                    Toast.makeText(signup.this,"Enter Signup Details", Toast.LENGTH_SHORT).show();

                }
                else if (s_email.isEmpty()) {
                    Toast.makeText(signup.this, "Enter Email", Toast.LENGTH_SHORT).show();

                } else if (s_password.isEmpty()) {

                    Toast.makeText(signup.this, "Enter Password", Toast.LENGTH_SHORT).show();

                } else if (s_phno.length()!=10) {
                    Toast.makeText(signup.this, "Number Should be 10 digits", Toast.LENGTH_SHORT).show();

                } else if (s_password.length()<8) {
                    Toast.makeText(signup.this, "Password Must Contain atleast 8 characters", Toast.LENGTH_SHORT).show();

                } else {
                    createuser();


                }
            }
        });


    }

    private void createuser()
    {
        String s_password=spassword.getText().toString();
        String s_email=semail.getText().toString();
        String s_name=sname.getText().toString();
        String s_phno=sphno.getText().toString();

        sauth.createUserWithEmailAndPassword(s_email, s_password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signup.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signup.this, loginpage.class));
                    finish();

                    db.child(sauth.getUid()).child("Name").setValue(s_name);
                    db.child(sauth.getUid()).child("Number").setValue(s_phno);

                    sendEmailVerification();
                } else {
                    Toast.makeText(signup.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                }
            }



        });


    }
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser=sauth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email is Sent To Your Email",Toast.LENGTH_SHORT).show();
                    sauth.signOut();
                    finish();
                   // startActivity(new Intent(signup.this, loginpage.class));


                }

            });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Failed To Send Verfication Email",Toast.LENGTH_SHORT).show();

        }
    }
}