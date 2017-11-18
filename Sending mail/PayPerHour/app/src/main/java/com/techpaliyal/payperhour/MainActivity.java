package com.techpaliyal.payperhour;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
ProgressBar pg;
    EditText name,addr,phn,task,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pg=(ProgressBar)findViewById(R.id.progressBar);
        name=(EditText)findViewById(R.id.editText);
        addr=(EditText)findViewById(R.id.editText2);
        phn=(EditText)findViewById(R.id.editText3);
        time=(EditText)findViewById(R.id.editText4);
        task=(EditText)findViewById(R.id.editText5);
    }
public void Send(View a)
{
    new Yogesh().execute("http://wscubetechapps.in/mobileteam/new_query/send_email.php?email_to=lxvaishnav@gmail.com&email_from=seeker@gmail.com&subject=form&message="+name.getText().toString()+"<br>"+addr.getText().toString()+"<br>");
}
    public class Yogesh extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL abc = new URL(strings[0]);

                HttpURLConnection con=(HttpURLConnection)abc.openConnection();
                con.setReadTimeout(10000);
                con.setConnectTimeout(10000);
                con.setRequestMethod("GET");
                con.connect();

                InputStream str= con.getInputStream();
                String xyz= convertInp_to_STR(str);
            }
            catch (Exception e){}

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pg.setVisibility(View.INVISIBLE);
        }
    }

    static String convertInp_to_STR(InputStream strm)
    {
        Scanner scn= new Scanner(strm);
        scn=scn.useDelimiter("\\A");

        return scn.hasNext()? scn.next():"";
    }
}
