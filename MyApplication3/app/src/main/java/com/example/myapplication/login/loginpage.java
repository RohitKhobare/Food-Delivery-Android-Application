package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.views.MainActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {
    TextView signup, forgot,googlebtn;
    Button login;
    FirebaseAuth firebaseAuth;
    public static String PREFS_NAME="MyPrefsFile";
    public static EditText email;
    EditText password;






    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        signup = findViewById(R.id.signin);
        login = findViewById(R.id.login);
        forgot = findViewById(R.id.forgot);
        googlebtn = findViewById(R.id.googlebtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginpage.this, com.example.myapplication.login.signup.class);
                startActivity(intent);

            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this, forgotpassword.class);
                startActivity(intent);
            }
        });
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        firebaseAuth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                  String lemail = email.getText().toString().trim();
                String lpassword = password.getText().toString().trim();

                if (lemail.isEmpty() && lpassword.isEmpty()) {
                    Toast.makeText(loginpage.this, "Enter Login Details", Toast.LENGTH_SHORT).show();

                } else if (lemail.isEmpty()) {
                    Toast.makeText(loginpage.this, "Enter Email", Toast.LENGTH_SHORT).show();

                } else if (lpassword.isEmpty()) {

                    Toast.makeText(loginpage.this, "Enter Password", Toast.LENGTH_SHORT).show();

                } else {

                    /*sauth.signInWithEmailAndPassword(lemail, lpassword).addOnSuccessListener(loginpage.this, new OnSuccessListener<AuthResult>() {

                        public void onSuccess(AuthResult authResult) {
                            SharedPreferences sharedPreferences=getSharedPreferences(loginpage.PREFS_NAME,0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();

                            editor.putBoolean("hasLoggedIn",true);
                            editor.commit();

                            Toast.makeText(loginpage.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginpage.this, MainActivity.class));

                            finish();

                        }

                    });*/

                    firebaseAuth.signInWithEmailAndPassword(lemail,lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {

                                SharedPreferences sharedPreferences=getSharedPreferences(loginpage.PREFS_NAME,0);
                                SharedPreferences.Editor editor=sharedPreferences.edit();

                                editor.putBoolean("hasLoggedIn",true);
                                editor.commit();


                                checkmailVerification();


                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }


        });
    }
    private void checkmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser.isEmailVerified() == true)
        {

            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(loginpage.this, MainActivity.class));


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Verfiy Your Mail First",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();

        }

    }

}


