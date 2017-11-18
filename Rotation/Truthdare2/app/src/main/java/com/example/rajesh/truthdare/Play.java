package com.example.rajesh.truthdare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Play extends AppCompatActivity {


//    Number obj;
ImageView bottle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        bottle=(ImageView)findViewById(R.id.imageView3);

    }


    int min = 1;
    int max = 50;


//int a =new Random().nextInt(360)+3600;

    public void play(View abc){

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        int b=i1*45*10;

            RotateAnimation rot = new RotateAnimation(0, b, RotateAnimation.RELATIVE_TO_SELF, .5f, RotateAnimation.RELATIVE_TO_SELF, .5f);
            rot.setDuration(3500);
            rot.setFillAfter(true);
            bottle.startAnimation(rot);
    }

    }
















