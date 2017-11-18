package com.thousandzero.quiz;

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

    public void Next(View a)
    {
        Intent abc= new Intent(getApplicationContext(),Home.class);

        if(a.getId()==R.id.button)   abc.putExtra("Blue","Gk");
        else if(a.getId()==R.id.button2)   abc.putExtra("Blue","Technology");
        else  abc.putExtra("Blue","ComputerScience");

        startActivity(abc);
    }
}
