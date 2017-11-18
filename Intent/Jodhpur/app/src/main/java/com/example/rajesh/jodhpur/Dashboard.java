package com.example.rajesh.jodhpur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void places(View avz){
        Intent ab=new Intent(getApplicationContext(),Places.class);
        startActivity(ab);
    }

    public void Hotel(View abc){
        Intent hot =new Intent(getApplicationContext(),Hotel.class);
        startActivity(hot);
    }

    public void Festival(View abc) {
        Intent hot = new Intent(getApplicationContext(), Hotel.class);
        startActivity(hot);
    }

    public void Food(View abc) {
        Intent hot = new Intent(getApplicationContext(), Hotel.class);
        startActivity(hot);
    }
}
