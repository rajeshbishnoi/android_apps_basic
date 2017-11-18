package com.example.rajesh.poiparsing;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    EditText lat,longs,radius,type,name;

    ProgressBar pg;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lat=(EditText)findViewById(R.id.editText);
        longs=(EditText)findViewById(R.id.editText2);
        radius=(EditText)findViewById(R.id.editText3);
        type=(EditText)findViewById(R.id.editText4);
        name=(EditText)findViewById(R.id.editText5);

        pg=(ProgressBar)findViewById(R.id.progressBar);
        lst=(ListView)findViewById(R.id.listview1);

    }

    public void Search(View a)
    {
        new BckFetcher.execute();
    }

    public class BckFetcher extends AsyncTask<String,Void,Rajesh>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Rajesh doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Rajesh rajesh) {
            super.onPostExecute(rajesh);
        }
    }
}

