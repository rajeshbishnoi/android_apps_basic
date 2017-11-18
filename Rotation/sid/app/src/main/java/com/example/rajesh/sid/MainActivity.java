package com.example.rajesh.sid;

//import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;

import android.widget.EditText;

import com.example.rajesh.sid.R;
import com.example.rajesh.sid.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BodyPartFragment headFragment=new BodyPartFragment();
        headFragment.setImageIds(AndroidImageAssets.getHeads());
        headFragment.setListIndex(1);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.head_container,headFragment).commit();

        BodyPartFragment bodyFragment= new BodyPartFragment();
        bodyFragment.setImageIds(AndroidImageAssets.getBodies());
        fragmentManager.beginTransaction().add(R.id.body_container,bodyFragment).commit();

        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setImageIds(AndroidImageAssets.getLegs());
        fragmentManager.beginTransaction().add(R.id.leg_container,legFragment).commit();

    }

}
