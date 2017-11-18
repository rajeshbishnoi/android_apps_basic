package com.wscubetech.ankit.contactfetcher;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lst;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> number = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lst = (ListView) findViewById(R.id.lst);

        getContactsInArray();

        lst.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, names));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent caller = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + number.get(position)));

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


                        Toast.makeText(MainActivity.this, "Not fetching", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(caller);

            }
        });

    }

    public void getContactsInArray() {
        try {

            Uri abc = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;


            ContentResolver cr = getContentResolver();

            String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
                    + " Collate Localized Asc";


            Cursor c = cr.query(abc, null, null, null, sortOrder);
            if (c.getCount() > 0) {
                Toast.makeText(MainActivity.this, "if", Toast.LENGTH_SHORT).show();

                while (c.moveToNext()) {

//                    Toast.makeText(MainActivity.this, "Not fetching", Toast.LENGTH_SHORT).show();

                    names.add(c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

                    number.add(c.getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
            }
        }catch (Exception e)
        {
e.printStackTrace();
        }
    }
}
