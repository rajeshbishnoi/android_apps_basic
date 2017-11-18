package com.example.rajesh.anil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void padharo(View ab){
        Intent abc=new Intent(getApplicationContext(),Pics.class);
        startActivity(abc);
    }
}
