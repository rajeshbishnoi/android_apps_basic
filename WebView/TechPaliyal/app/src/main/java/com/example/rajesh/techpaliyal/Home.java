package com.example.rajesh.techpaliyal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void Facebook(View abc)
    {
        Intent ab=new Intent(getApplicationContext(),MainActivity.class);
        ab.putExtra("blue","http://fb.com");
        startActivity(ab);
    }

    public void Twitter(View abc)
    {
        Intent ab=new Intent(getApplicationContext(),MainActivity.class);
        ab.putExtra("blue","http://twitter.com");
        startActivity(ab);
    }

    public void Linkedin(View abc)
    {
        Intent ab=new Intent(getApplicationContext(),MainActivity.class);
        ab.putExtra("blue","http://fb.com");
        startActivity(ab);
    }
}
