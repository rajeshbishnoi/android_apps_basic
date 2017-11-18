package com.example.rajesh.drawing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
DrawingView draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draw=(DrawingView)findViewById(R.id.draw);
    }

    public void ChangeColor(View a)
    {
        if(a.getId()==R.id.b1) draw.clr= Color.parseColor("#ff0000");
        if(a.getId()==R.id.b2) draw.clr= Color.parseColor("#00ff00");
        if(a.getId()==R.id.b3) draw.clr= Color.parseColor("#0000ff");
        if(a.getId()==R.id.b4) draw.clr= Color.parseColor("#000000");
        if(a.getId()==R.id.b5) draw.clr= Color.parseColor("#ffffff");


    }

    public void ChangeSize(View a)
    {
        if(a.getId()==R.id.b6) draw.STROKE_WIDTH+=2;
        if(a.getId()==R.id.b7) draw.STROKE_WIDTH-=2;



    }

    public void Clear(View a)
    {
        draw.clear();
    }

    public void Undo(View a)
    {
        draw.undo();
    }


}
