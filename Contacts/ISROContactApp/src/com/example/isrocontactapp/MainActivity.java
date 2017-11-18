package com.example.isrocontactapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView lst;
	ArrayList<String> contactsArr = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lst = (ListView) findViewById(R.id.listView1);
		getContactsInArray();
		lst.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, contactsArr));
	}

	public void getContactsInArray() {

		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		ContentResolver cr = getContentResolver();
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " Collate Localized Asc";

		Cursor c = cr.query(uri, null, null, null, sortOrder);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				contactsArr
						.add(c.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

				contactsArr
						.add(c.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

			}
		}
	}
}
