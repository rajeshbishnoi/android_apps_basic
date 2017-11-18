package com.example.rajesh.truthdare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Number extends AppCompatActivity {

    EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        number=(EditText)findViewById(R.id.editText2);

    }


public void letsplay(View abc){
    int num=Integer.parseInt(number.getText().toString());

        Intent abcd=new Intent(getApplicationContext(),Play.class);
        abcd.putExtra("num","number");
    startActivity(abcd);
}
}
