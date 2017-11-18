package com.example.rajesh.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread obj= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent abc= new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(abc);
                        }
                    });

                }
                catch (Exception e)
                {

                }

            }
        });

        obj.start();
    }
}
