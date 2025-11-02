package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.views.MainActivity;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences(loginpage.PREFS_NAME,0);
                boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);
                if(hasLoggedIn)
                {
                    Intent intent = new Intent(splashscreen.this, MainActivity .class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(splashscreen.this, loginpage .class);
                    startActivity(intent);
                    finish();
                }



            }
        },2000);
    }
}