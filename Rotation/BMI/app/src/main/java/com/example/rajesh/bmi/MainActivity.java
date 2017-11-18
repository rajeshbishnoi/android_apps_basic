package com.example.rajesh.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText feet,inch,kg;
    ImageView arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feet=(EditText)findViewById(R.id.editText3);
        inch=(EditText)findViewById(R.id.editText);
        kg=(EditText)findViewById(R.id.editText2);

        arrow=(ImageView)findViewById(R.id.imageView);
    }


    public void Calculate(View a)
    {
        int ft= Integer.parseInt(feet.getText().toString());
        int inc= Integer.parseInt(inch.getText().toString());
        int kgs= Integer.parseInt(kg.getText().toString());

        double BMI= (kgs*2.204*703)/((ft*12+inc)*(ft*12+inc));

        if(BMI<20) Toast.makeText(getApplicationContext(),"Underweight",Toast.LENGTH_LONG).show();
        else if(BMI>30) Toast.makeText(getApplicationContext(),"Overweight",Toast.LENGTH_LONG).show();
        else Toast.makeText(getApplicationContext(),"Healthy",Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(),"Your BMI is "+BMI,Toast.LENGTH_LONG).show();

int toangle= (int)(3.6*BMI);
        RotateAnimation rot= new RotateAnimation(0,toangle,RotateAnimation.RELATIVE_TO_SELF,0.85f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rot.setFillAfter(true);
        rot.setDuration(4000);
        arrow.startAnimation(rot);
    }
}
