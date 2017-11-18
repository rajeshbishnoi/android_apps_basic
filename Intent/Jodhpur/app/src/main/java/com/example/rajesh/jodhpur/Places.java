package com.example.rajesh.jodhpur;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
    }

public void Next(View avx){
        Intent abc=new Intent(getApplicationContext(),Places2.class);
        startActivity(abc);


    SmsManager mysms=SmsManager.getDefault();
    mysms.sendTextMessage("8432594779",null,"Please do not react",null, PendingIntent.readPendingIntentOrNullFromParcel(null));
}

}
